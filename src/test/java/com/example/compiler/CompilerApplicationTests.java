package com.example.compiler;

import com.example.compiler.entity.WrongMessage;
import com.example.compiler.lexer.Lexer;
import com.example.compiler.llParser.*;
import com.example.compiler.token.Token;
import com.example.compiler.token.TokenType;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest
@SuppressWarnings("all")
class CompilerApplicationTests {
    @Test
    void contextLoads() throws IOException {
        Lexer lexer = new Lexer();
        String input = lexer.ReadFile("src/3.txt");
        lexer.tokenize(input);
        List<Token> tokens = lexer.getFilteredTokens();
        StringBuilder res = new StringBuilder();
        for (Token token : tokens) {
            res.append(token);
            res.append("\n");
        }
        System.out.println(res.toString());

        for (Map.Entry<Pair<Integer, Integer>, WrongMessage> entry : lexer.getWrongList().entrySet()) {
            Pair<Integer, Integer> resultPair = entry.getKey();
            int row = resultPair.getKey();
            int col = resultPair.getValue();
            System.out.println("行：" + row + "， 列：" + col + "，此处的字符串\"" + entry.getValue().getTokenContent() + "\"附近或许存在错误，提示：" +
                    entry.getValue().getErrorCode().getMessage());
        }

    }

    @Test
    void czh() {
        List<Object> list = new ArrayList<Object>() {{
            add(TokenType.DIVIDE);
            add(NonTerminalType.WHILESTMT);
        }};
        for (Object i : list) {
            if (i instanceof TokenType) {
                System.out.println(i);
            }
        }
    }

    @Test
    void czh2() {
        LLUtil llUtil=new LLUtil();
        String[][] res = llUtil.printParsingTable();
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res.length;j++){
                System.out.print(res[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    void czh3() throws IOException {
        Lexer lexer = new Lexer();
        String input = lexer.ReadFile("src/3.txt");
        System.out.println("--------语法开始 ------");
        LLParser llParser = new LLParser(input);
    }

    @Test
    void testFirstSet() throws IOException {
        Lexer lexer = new Lexer();
        String input = lexer.ReadFile("src/3.txt");
        System.out.println("--------语法开始 ------");
        LLParser llParser = new LLParser(input);
        llParser.getFirstSet();
        llParser.getFollowSet();
//        llParser.printproductionMap();
//        llParser.printFirstAFollow();
    }

    @Test
    void testPrint() throws IOException {
        Lexer lexer = new Lexer();
        String input = lexer.ReadFile("src/3.txt");
        System.out.println("--------语法开始 ------");
        LLParser llParser = new LLParser(input);
        llParser.printproductionMap();
    }

    @Test
    void testFirstAndFolloeSetForCzh() {
        LLUtil llUtil = new LLUtil();
//        HashMap<Object, Set<TokenType>> FirstSet =  llUtil.getFirstSet();
//        HashMap<Object, Set<TokenType>> FollowSet =  llUtil.getFollowSet();
        HashMap<Pair<NonTerminalType, TokenType>, Object> parsingTable = llUtil.getParsingTable();
        for (Map.Entry<Pair<NonTerminalType, TokenType>, Object> entry : parsingTable.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }

//    @Test
//    /**
//     * @Description
//     * @Author Hu Zirui
//     * @Throws
//     */
//    void testFinalTable() throws IOException {
//        Lexer lexer = new Lexer();
//        String input = lexer.ReadFile("src/3.txt");
//        System.out.println("--------语法开始 ------");
//        LLParser llParser = new LLParser(input);
//        llParser.buildTable();
//    }


}
