package project;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private static final int up = 0, down = 1, left = 2, right = 3;
    private static Entity[][] board;
    private static final SnakeEntity dummySnake = new SnakeEntity(-1, -1);
    private static final AppleEntity dummyApple = new AppleEntity(-1, -1);

    private static void drawGrids() {
        StdDraw.setPenColor(Color.black);
        for (int x = 1 ; x < 25 ; ++x) {
            double x0 = x * 0.04;
            StdDraw.line(x0, 0, x0, 1);
        }
        for (int y = 1 ; y < 25 ; ++y) {
            double y0 = y * 0.04;
            StdDraw.line(0, y0, 1, y0);
        }
    }

    private static void generateApple(AppleEntity apple) {
        while (true) {
            int xPos = (int) (Math.random() * 25);
            int yPos = (int) (Math.random() * 25);

            if (board[xPos][yPos] == null
                    || (board[xPos][yPos].getClass() != dummySnake.getClass()
                    && board[xPos][yPos].getClass() != dummyApple.getClass())) {
                apple.setxPos(xPos);
                apple.setyPos(yPos);
                return;
            }
        }
    }

    private static Entity[][] updateBoard(Snake snake, AppleEntity apple) {
        Entity[][] board = new Entity[25][25];

        for(SnakeEntity snakeEntity : snake.getSnakeList()) {
            if (!(snakeEntity.getXPos() < 0 || snakeEntity.getXPos() > 24 || snakeEntity.getYPos() < 0 || snakeEntity.getYPos() > 24)) {
                board[snakeEntity.getXPos()][snakeEntity.getYPos()] = snakeEntity;
            }
        }
        if (!(apple.getXPos() < 0 || apple.getXPos() > 24 || apple.getYPos() < 0 || apple.getYPos() > 24)) {
            board[apple.getXPos()][apple.getYPos()] = apple;
        }

        return board;
    }

    public static void main(String[] args) {
        board = new Entity[25][25];

        SnakeEntity se0 = new SnakeEntity(16, 13);
        SnakeEntity se1 = new SnakeEntity(17, 13);
        SnakeEntity se2 = new SnakeEntity(18, 13);
        List<SnakeEntity> snakeList = new LinkedList<>();
        snakeList.add(se0);
        snakeList.add(se1);
        snakeList.add(se2);
        Snake snake = new Snake(snakeList, left);

        AppleEntity apple = new AppleEntity(9, 13);
        board = updateBoard(snake, apple);

        //draw initial game
        StdDraw.clear();
        drawGrids();
        snake.draw();
        apple.draw();
        StdDraw.pause(1000);
        snake.move();

        while (true) {
            SnakeEntity head = snake.getHead();
            if (head.getXPos() < 0 || head.getYPos() < 0 || head.getXPos() > 24 || head.getYPos() > 24
                    || (board[head.getXPos()][head.getYPos()] != null
                    && board[head.getXPos()][head.getYPos()].getClass() == dummySnake.getClass())) {
                System.out.println("GAME OVER");
                int score = snake.getSnakeList().size() - 3;
                System.out.println("SCORE: " + score);
                System.out.println("PRESS 'R' TO REPLAY\n\n\n");
                break;
            }
            if (snake.getSnakeList().size() == 625) {
                System.out.println("YOU HAVE BEATEN THE GAME");
                break;
            }
            if (board[head.getXPos()][head.getYPos()] == apple) {
                snake.grow(apple);
                generateApple(apple);
            }
            board = updateBoard(snake, apple);

            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) snake.changeDirection(up);
            else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) snake.changeDirection(down);
            else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) snake.changeDirection(left);
            else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) snake.changeDirection(right);
            snake.move();

            StdDraw.enableDoubleBuffering();
            StdDraw.show();
            StdDraw.pause(69);

            StdDraw.clear();
            drawGrids();
            apple.draw();
            snake.draw();
        }

        while (true) {
            StdDraw.pause(1);
            if (StdDraw.isKeyPressed(KeyEvent.VK_R)) main(args);
            if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) return;
        }
     }

}
