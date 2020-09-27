package ee.taltech.iti02032020.backend.request;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import okhttp3.Connection;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CoronaRequest {

    public CoronaRequest() {

    }
    public String CoronaRequestCountry(String country) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://coronavirus-map.p.rapidapi.com/v1/summary/region?region=" + country)
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "93172dfd82mshaeae41651c77f61p1382d2jsn6fe87dae6648")
                .build();

        return client.newCall(request).execute().body().string();
    }

    public CoronaVirus addRequestToDatabase(String string, String country) {
        // country
        // infected right now (today)
        // infected overall (total cases)
        // recovered overall (total cases)
        // overall deaths ( total cases )
//        JsonObject jsonObject = new JSONObject(string);
//        Gson gson = new Gson();
//         = gson.toJson(string);
//         JSONObject jsonObject = parser.parse
        JsonObject json = new Gson().fromJson(string, JsonObject.class);
        System.out.println(country);
        System.out.println(json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("total_cases"));
        System.out.println(json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("recovered"));
        System.out.println(json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("deaths"));
        System.out.println(json.get("data").getAsJsonObject().get("change").getAsJsonObject().get("total_cases"));
        String totalCases = json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("total_cases").toString();
        String totalRecovered = json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("recovered").toString();
        String totalDeaths = json.get("data").getAsJsonObject().get("summary").getAsJsonObject().get("deaths").toString();
        String currentCases = json.get("data").getAsJsonObject().get("change").getAsJsonObject().get("total_cases").toString();
        CoronaVirus coronaVirus = new CoronaVirus(country, totalCases, totalRecovered, totalDeaths, currentCases);
        return coronaVirus;
    }

//    public void addToDataBase(Connection con, String dbName) throws SQLException {
//        throw new SQLException() {
//
//            Statement stmt;
//            String query = "select COF_NAME, SUP_ID, PRICE, " +
//                    "SALES, TOTAL " +
//                    "from " + dbName + ".COFFEES";
//            try {
//                stmt = con.createStatement();
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    String coffeeName = rs.getString("COF_NAME");
//                    int supplierID = rs.getInt("SUP_ID");
//                    float price = rs.getFloat("PRICE");
//                    int sales = rs.getInt("SALES");
//                    int total = rs.getInt("TOTAL");
//                    System.out.println(coffeeName + "\t" + supplierID +
//                            "\t" + price + "\t" + sales +
//                            "\t" + total);
//                }
//            } catch (SQLException e ) {
//                JDBCTutorialUtilities.printSQLException(e);
//            } finally {
//                if (stmt != null) { stmt.close(); }
//            }
//    }
//
//    }
}
