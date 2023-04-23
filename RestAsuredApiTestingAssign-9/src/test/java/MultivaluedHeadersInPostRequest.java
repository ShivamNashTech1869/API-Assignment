import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MultivaluedHeadersInPostRequest {
    private static final String POST_REQUEST_METHOD = "POST";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static void main(String[] args) {
        String url = "https://reqres.in/api/users";
        String postData = "{\"name\": \"Shivam Singh\", \"job\": \"QA Engineer Knoldus\"}";
        List<String> authHeaders = Arrays.asList("Bearer token1", "Bearer token2");

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod(POST_REQUEST_METHOD);
            connection.setRequestProperty(CONTENT_TYPE_HEADER, APPLICATION_JSON);

            //set the headers in the request
            for (String authHeader : authHeaders) {
                connection.setRequestProperty(AUTHORIZATION_HEADER, authHeader);
            }

            connection.setDoOutput(true);
            connection.getOutputStream().write(postData.getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
