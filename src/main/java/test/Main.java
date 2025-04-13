package test;


public class Main {

    public static void main(String[] args) {

      System.out.println(createErrorResponse("Hallo Welt!", 200));
    }


    static class HttpHeader {
        private String name;
        private String value;

        public HttpHeader(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name + ": " + value;
        }
    }

    static class HttpResponse {
        private int statusCode;
        private HttpHeader[] headers;
        private String body;

        // Konstruktor gemäß Klassendiagramm
        public HttpResponse(int statusCode) {
            this.statusCode = statusCode;
            this.headers = new HttpHeader[0]; // Initial leer
            this.body = "";
        }

        // Methode zum Hinzufügen eines Headers
        public void addHeader(String name, String value) {
            HttpHeader[] newHeaders = new HttpHeader[headers.length + 1];
            System.arraycopy(headers, 0, newHeaders, 0, headers.length);

            newHeaders[headers.length] = new HttpHeader(name, value);
            this.headers = newHeaders;
        }

        // Methode zum Setzen des Bodys
        public void setBody(String content) {
            this.body = content;
        }

        // Getter für statusCode
        public int getStatusCode() {
            return statusCode;
        }

        // Getter für headers
        public HttpHeader[] getHeaders() {
            return headers;
        }

        // Getter für body
        public String getBody() {
            return body;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("HTTP < " + statusCode + " >\n");
            for (HttpHeader header : headers) {
                result.append(header.toString()).append("\n");
            }
            result.append("< Response Body: ").append(body).append((" >"));
            return result.toString();
        }
    }

    public static HttpResponse createErrorResponse(String nachricht, int statusCode) {
        HttpResponse response = new HttpResponse(statusCode);
        response.addHeader("Content-Type", "text/plain");
        response.addHeader("Content-Length", "< " + nachricht.length() + " >");
        response.setBody(nachricht);
        return response;
    }


}

