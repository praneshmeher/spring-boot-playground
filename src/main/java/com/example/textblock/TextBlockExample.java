package com.example.textblock;

public class TextBlockExample {

    public static void main(String[] args) {
        // Basic multiline text block
        String poem = """
                Roses are red,
                Violets are blue,
                Text blocks make
                multiline strings easy.
                """;
        System.out.println("Poem:\n" + poem);

        // HTML snippet — demonstrates preserved formatting and stripIndent()
        String html = """
                <html>
                    <body>
                        <p>Hello, Text Blocks!</p>
                    </body>
                </html>
                """;
        System.out.println("HTML (stripped indentation):\n" + html.stripIndent());

        // Demonstrate translateEscapes(): write literal escape sequences in the block and convert them
        String escaped = """
                First line\\nSecond line\\tTabbed
                """.translateEscapes();
        System.out.println("Translate escapes:\n" + escaped);

        // JSON with quotes — no need to escape double quotes inside the block (except triple quotes)
        String json = """
                {
                    "name": "Alice",
                    "message": "She said \"Hello\""
                }
                """;
        System.out.println("JSON:\n" + json);
    }

}
