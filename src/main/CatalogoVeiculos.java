import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CatalogoVeiculos {

    static class Veiculo {
        int id;
        String marca, modelo, cor;
        int ano;
        boolean vendido;
        LocalDateTime criadoEm = LocalDateTime.now();

        public String toJson() {
            return String.format(
                "{\"id\":%d,\"marca\":\"%s\",\"modelo\":\"%s\",\"cor\":\"%s\",\"ano\":%d,\"vendido\":%s,\"criadoEm\":\"%s\"}",
                id, marca, modelo, cor, ano, vendido, criadoEm.toString());
        }

        public static Veiculo fromJson(String json) {
            Veiculo v = new Veiculo();
            json = json.replaceAll("[{}\"]", "");
            String[] campos = json.split(",");
            for (String campo : campos) {
                String[] partes = campo.split(":");
                if (partes.length < 2) continue;
                String key = partes[0].trim();
                String value = partes[1].trim();
                switch (key) {
                    case "marca": v.marca = value; break;
                    case "modelo": v.modelo = value; break;
                    case "cor": v.cor = value; break;
                    case "ano": v.ano = Integer.parseInt(value); break;
                    case "vendido": v.vendido = Boolean.parseBoolean(value); break;
                }
            }
            return v;
        }
    }

    static List<Veiculo> banco = new ArrayList<>();
    static int nextId = 1;
    static Set<String> marcasValidas = Set.of("Ford", "Chevrolet", "Volkswagen", "Honda", "Toyota", "Fiat");

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/veiculos", new VeiculoHandler());
        server.setExecutor(null);
        System.out.println("Servidor rodando em http://localhost:8000");
        server.start();
    }

    static class VeiculoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            String metodo = ex.getRequestMethod();
            String caminho = ex.getRequestURI().getPath();
            String query = ex.getRequestURI().getQuery();
            String resposta = "";
            int status = 200;

            try {
                if (metodo.equals("GET") && caminho.equals("/veiculos")) {
                    resposta = "[" + banco.stream().map(Veiculo::toJson).collect(Collectors.joining(",")) + "]";
                }

                else if (metodo.equals("GET") && caminho.matches("/veiculos/\\d+")) {
                    int id = Integer.parseInt(caminho.split("/")[2]);
                    Veiculo v = banco.stream().filter(veic -> veic.id == id).findFirst().orElse(null);
                    resposta = (v != null) ? v.toJson() : "{\"erro\":\"Veículo não encontrado\"}";
                    status = (v != null) ? 200 : 404;
                }

                else if (metodo.equals("POST") && caminho.equals("/veiculos")) {
                    String corpo = new String(ex.getRequestBody().readAllBytes());
                    Veiculo v = Veiculo.fromJson(corpo);
                    if (!marcasValidas.contains(v.marca)) {
                        resposta = "{\"erro\":\"Marca inválida\"}";
                        status = 400;
                    } else {
                        v.id = nextId++;
                        banco.add(v);
                        resposta = v.toJson();
                    }
                }

                else if (metodo.equals("PUT") && caminho.matches("/veiculos/\\d+")) {
                    int id = Integer.parseInt(caminho.split("/")[2]);
                    String corpo = new String(ex.getRequestBody().readAllBytes());
                    Veiculo novo = Veiculo.fromJson(corpo);
                    boolean achou = false;
                    for (Veiculo v : banco) {
                        if (v.id == id) {
                            if (!marcasValidas.contains(novo.marca)) {
                                resposta = "{\"erro\":\"Marca inválida\"}";
                                status = 400;
                                break;
                            }
                            v.marca = novo.marca;
                            v.modelo = novo.modelo;
                            v.ano = novo.ano;
                            v.cor = novo.cor;
                            v.vendido = novo.vendido;
                            resposta = v.toJson();
                            achou = true;
                            break;
                        }
                    }
                    if (!achou && status == 200) {
                        resposta = "{\"erro\":\"Veículo não encontrado\"}";
                        status = 404;
                    }
                }

                else if (metodo.equals("PATCH") && caminho.matches("/veiculos/\\d+")) {
                    int id = Integer.parseInt(caminho.split("/")[2]);
                    String corpo = new String(ex.getRequestBody().readAllBytes());
                    Veiculo patch = Veiculo.fromJson(corpo);
                    Veiculo v = banco.stream().filter(veic -> veic.id == id).findFirst().orElse(null);
                    if (v == null) {
                        resposta = "{\"erro\":\"Veículo não encontrado\"}";
                        status = 404;
                    } else {
                        if (patch.marca != null && marcasValidas.contains(patch.marca)) v.marca = patch.marca;
                        if (patch.modelo != null) v.modelo = patch.modelo;
                        if (patch.cor != null) v.cor = patch.cor;
                        if (patch.ano != 0) v.ano = patch.ano;
                        v.vendido = patch.vendido;
                        resposta = v.toJson();
                    }
                }

                else if (metodo.equals("DELETE") && caminho.matches("/veiculos/\\d+")) {
                    int id = Integer.parseInt(caminho.split("/")[2]);
                    boolean removido = banco.removeIf(v -> v.id == id);
                    resposta = removido ? "{\"mensagem\":\"Removido\"}" : "{\"erro\":\"Veículo não encontrado\"}";
                    status = removido ? 200 : 404;
                }

                else if (metodo.equals("GET") && caminho.equals("/veiculos/nao-vendidos")) {
                    long count = banco.stream().filter(v -> !v.vendido).count();
                    resposta = "{\"naoVendidos\":" + count + "}";
                }

                else if (metodo.equals("GET") && caminho.equals("/veiculos/por-decada")) {
                    Map<String, Long> porDecada = banco.stream().collect(Collectors.groupingBy(
                        v -> (v.ano / 10 * 10) + "s", Collectors.counting()
                    ));
                    resposta = mapToJson(porDecada);
                }

                else if (metodo.equals("GET") && caminho.equals("/veiculos/por-marca")) {
                    Map<String, Long> porMarca = banco.stream().collect(Collectors.groupingBy(
                        v -> v.marca, Collectors.counting()
                    ));
                    resposta = mapToJson(porMarca);
                }

                else if (metodo.equals("GET") && caminho.equals("/veiculos/recentes")) {
                    LocalDateTime semanaAtras = LocalDateTime.now().minusDays(7);
                    List<Veiculo> recentes = banco.stream().filter(v -> v.criadoEm.isAfter(semanaAtras)).toList();
                    resposta = "[" + recentes.stream().map(Veiculo::toJson).collect(Collectors.joining(",")) + "]";
                }

                else if (metodo.equals("GET") && caminho.equals("/veiculos/filtro")) {
                    Map<String, String> params = queryToMap(query);
                    String marca = params.get("marca");
                    String cor = params.get("cor");
                    String anoStr = params.get("ano");

                    List<Veiculo> filtrado = banco.stream()
                        .filter(v -> (marca == null || v.marca.equalsIgnoreCase(marca)) &&
                                     (cor == null || v.cor.equalsIgnoreCase(cor)) &&
                                     (anoStr == null || v.ano == Integer.parseInt(anoStr)))
                        .toList();

                    resposta = "[" + filtrado.stream().map(Veiculo::toJson).collect(Collectors.joining(",")) + "]";
                }

                else {
                    resposta = "{\"erro\":\"Rota não encontrada\"}";
                    status = 404;
                }

            } catch (Exception e) {
                resposta = "{\"erro\":\"Erro interno: " + e.getMessage() + "\"}";
                status = 500;
            }

            ex.getResponseHeaders().set("Content-Type", "application/json");
            ex.sendResponseHeaders(status, resposta.getBytes().length);
            OutputStream os = ex.getResponseBody();
            os.write(resposta.getBytes());
            os.close();
        }
    }

    static String mapToJson(Map<String, Long> map) {
        return map.entrySet().stream()
            .map(e -> "\"" + e.getKey() + "\":" + e.getValue())
            .collect(Collectors.joining(",", "{", "}"));
    }

    static Map<String, String> queryToMap(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null) return map;
        for (String param : query.split("&")) {
            String[] partes = param.split("=");
            if (partes.length == 2) map.put(partes[0], partes[1]);
        }
        return map;
    }
}
