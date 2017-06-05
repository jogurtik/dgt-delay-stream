package com.jogo.common;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PgnFileTest {
    @Test
    public void test1() throws IOException {
        PgnFile PgnFile = new PgnFile();

        String pgnFile = "[Event \"European Individual Chess Championship 2017\"]\n" +
                "[Site \"Minsk\"]\n" +
                "[Date \"2017.06.05\"]\n" +
                "[Round \"6.1\"]\n" +
                "[White \"Melkumyan, Hrant\"]\n" +
                "[Black \"Cheparinov, Ivan\"]\n" +
                "[Result \"*\"]\n" +
                "[BlackElo \"2688\"]\n" +
                "[WhiteElo \"2613\"]\n" +
                "[LiveChessVersion \"1.4.8\"]\n" +
                "[ECO \"D02\"]\n" +
                "\n" +
                "1. Nf3 {[%clk 1:24:35]} d5 {[%clk 1:30:52]} 2. d4 {[%clk 1:24:33]} Nf6\n" +
                "{[%clk 1:31:18]} 3. c4 {[%clk 1:24:57]} e6 {[%clk 1:31:44]} 4. g3\n" +
                "{[%clk 1:25:18]} Bb4+ {[%clk 1:32:03]} 5. Bd2 {[%clk 1:25:41]} Be7\n" +
                "{[%clk 1:32:28]} 6. Bg2 {[%clk 1:26:05]} O-O {[%clk 1:32:49]} 7. O-O\n" +
                "{[%clk 1:26:28]} Nbd7 {[%clk 1:33:13]} 8. a4 {[%clk 1:26:12]} a5 {[%clk 1:32:48]}\n" +
                "9. Qc2 {[%clk 1:26:32]} c6 {[%clk 1:33:11]} 10. Rc1 {[%clk 1:26:43]} Ne4\n" +
                "{[%clk 1:32:57]} 11. Be3 {[%clk 1:26:59]} Nd6 {[%clk 1:31:35]} *";

        int boardNumber = 2;
        int status = PgnFile.Check(pgnFile, boardNumber);
        assertEquals(status, -1);

        boardNumber = 1;
        status = PgnFile.Check(pgnFile, boardNumber);
        assertEquals(status, 0);

        pgnFile = "[Event \"European Individual Chess Championship 2017\"]\n" +
                "[Site \"Minsk\"]\n" +
                "[Date \"2017.06.05\"]\n" +
                "[Round \"6.1\"]\n" +
                "[White \"Melkumyan, Hrant\"]\n" +
                "[Black \"Cheparinov, Ivan\"]\n" +
                "[Result \"*\"]\n" +
                "[BlackElo \"2688\"]\n" +
                "[WhiteElo \"2613\"]\n" +
                "[LiveChessVersion \"1.4.8\"]\n" +
                "[ECO \"D02\"]\n" +
                "\n" +
                "1. Nf3 {[%clk 1:24:35]} d5 {[%clk 1:30:52]} 2. d4 {[%clk 1:24:33]} Nf6\n" +
                "{[%clk 1:31:18]} 3. c4 {[%clk 1:24:57]} e6 {[%clk 1:31:44]} 4. g3\n" +
                "{[%clk 1:25:18]} Bb4+ {[%clk 1:32:03]} 5. Bd2 {[%clk 1:25:41]} Be7\n" +
                "{[%clk 1:32:28]} 6. Bg2 {[%clk 1:26:05]} O-O {[%clk 1:32:49]} 7. O-O\n" +
                "{[%clk 1:26:28]} Nbd7 {[%clk 1:33:13]} 8. a4 {[%clk 1:26:12]} a5 {[%clk 1:32:48]}\n" +
                "9. Qc2 {[%clk 1:26:32]} c6 {[%clk 1:33:11]} 10. Rc1 {[%clk 1:26:43]} Ne4\n" +
                "{[%clk 1:32:57]} 11. Be3 {[%clk 1:26:59]} Nd6 {[%clk 1:31:35]}";

        boardNumber = 1;
        status = PgnFile.Check(pgnFile, boardNumber);
        assertEquals(status, -2);

        pgnFile = "[Event \"European Individual Chess Championship 2017\"]\n" +
                "[Site \"Minsk\"]\n" +
                "[Date \"2017.06.05\"]\n" +
                "[Round \"6.1\"]\n" +
                "[White \"Melkumyan, Hrant\"]\n" +
                "[Black \"Cheparinov, Ivan\"]\n" +
                "[Result \"1-0\"]\n" +
                "[BlackElo \"2688\"]\n" +
                "[WhiteElo \"2613\"]\n" +
                "[LiveChessVersion \"1.4.8\"]\n" +
                "[ECO \"D02\"]\n" +
                "\n" +
                "1. Nf3 {[%clk 1:24:35]} d5 {[%clk 1:30:52]} 2. d4 {[%clk 1:24:33]} Nf6\n" +
                "{[%clk 1:31:18]} 3. c4 {[%clk 1:24:57]} e6 {[%clk 1:31:44]} 4. g3\n" +
                "{[%clk 1:25:18]} Bb4+ {[%clk 1:32:03]} 5. Bd2 {[%clk 1:25:41]} Be7\n" +
                "{[%clk 1:32:28]} 6. Bg2 {[%clk 1:26:05]} O-O {[%clk 1:32:49]} 7. O-O\n" +
                "{[%clk 1:26:28]} Nbd7 {[%clk 1:33:13]} 8. a4 {[%clk 1:26:12]} a5 {[%clk 1:32:48]}\n" +
                "9. Qc2 {[%clk 1:26:32]} c6 {[%clk 1:33:11]} 10. Rc1 {[%clk 1:26:43]} Ne4\n" +
                "{[%clk 1:32:57]} 11. Be3 {[%clk 1:26:59]} Nd6 {[%clk 1:31:35]} 1-0";

        boardNumber = 1;
        status = PgnFile.Check(pgnFile, boardNumber);
        assertEquals(status, 2);

    }
}
