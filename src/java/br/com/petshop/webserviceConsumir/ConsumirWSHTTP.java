package br.com.petshop.webserviceConsumir;

import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.Raca;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class ConsumirWSHTTP {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        ConsumirWSHTTP http = new ConsumirWSHTTP();
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        Type clienteType = new TypeToken<Cliente>() {
        }.getType();
        Type clienteTypeLista = new TypeToken<List<Cliente>>() {
        }.getType();
       

        //Consultar Cliente
        String url = "http://localhost:8080/WebAppPetshop2/webresources/petshop/cliente/list";
        String json = http.sendGet(url, "GET");
        List<Cliente> lista = new LinkedList<>();
        lista = gson.fromJson(json, clienteTypeLista);

        //listar os clientes
        for (Cliente cliente1 : lista) {
            System.out.println("CPF....:" + cliente1.getCPF());
            System.out.println("Nome...:" + cliente1.getNome());
        }

        //inserir cliente
        //se quiser alterar, tem que pegar o ID primeiro
//        cliente.setId(7);
        cliente.setNome("Samuel Jackson");
        cliente.setCPF("123.123.123.43");
        cliente.setEmail("email@do.cliente");
        cliente.setRG("111.222.444");
        cliente.setTelefone("9999-1111");
        cliente.setEndereco("Rua azul e amarela");
        String json2 = gson.toJson(cliente, clienteType);
        //para alterar apenas mudar a url para alterar
//        String urlInserir = "http://localhost:8080/WebAppPetShop2/webresources/petshop/cliente/alterar";
        String urlInserir = "http://localhost:8080/WebAppPetshop2/webresources/petshop/cliente/inserir/";
        //e mudar este parâmetro para PUT
//        String retorno = http.sendPost(urlInserir, json2, "PUT");
        String retorno = http.sendPost(urlInserir, json2, "POST");
        System.out.println(retorno);

        Animal animal = new Animal();
        Type animalType = new TypeToken<Animal>() {
        }.getType();
        
        animal.setNome("Bob");
        animal.setIdade(5);
        Raca raca = new Raca();
        raca.setId(1);
        animal.setRaca(raca);
        cliente.setId(1);
        animal.setProprietario(cliente);
        String json3 = gson.toJson(animal, animalType);
        String urlInserir2 = "http://localhost:8080/WebAppPetshop2/webresources/petshop/animal/inserir/";
        
        String retorno2 = http.sendPost(urlInserir2, json3, "POST");
        System.out.println(retorno2);
        
        
        //excluir cliente
//        String urlExcluir = "http://localhost:8080/WebAppPetShop2/webresources/petshop/cliente/excluir/4";
//        String retorno2 = http.sendGet(urlExcluir, "GET");
//        System.out.println(retorno);
        
    }

    // HTTP GET request
    private String sendGet(String url, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

    // HTTP POST request
    private String sendPost(String url, String urlParameters, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

}
