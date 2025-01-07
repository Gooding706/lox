package jlox.src;

public class Token {
    final TokenType type;
    final int lineNumber;
    final String lexeme;
    final Object literal;

    Token(TokenType type, String lexeme, Object literal,int lineNumber) {
        this.type = type;
        this.lineNumber = lineNumber;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    public String toString()
    {
        return type + " " + lexeme + " " + literal;
    }
}
