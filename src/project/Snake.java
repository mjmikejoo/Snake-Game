package project;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    private final int up = 0, down = 1, left = 2, right = 3, none = 4;

    private List<SnakeEntity> snakeList;
    private SnakeEntity head;
    private int direction; //0 up 1 down 2 left 3 right

    public Snake(List<SnakeEntity> initList, int direction) {
        head = initList.get(0);
        snakeList = initList;
        this.direction = direction;
    }

    public void changeDirection(int direction) {
        if (this.direction == 0 || this.direction == 1) {
            if (direction == 2 || direction == 3) this.direction = direction;
        } else if (this.direction == 2 || this.direction == 3) {
            if (direction == 0 || direction == 1) this.direction = direction;
        }
    }

    public void grow(AppleEntity apple) {
        int xPos = apple.getXPos();
        int yPos = apple.getYPos();

        SnakeEntity snakeEntity = new SnakeEntity(xPos, yPos);
        snakeList.add(0, snakeEntity);
        head = snakeEntity;
    }

    public void move() {
        List<SnakeEntity> newSnakeList = new LinkedList<>();
        for (SnakeEntity snakeEntity : snakeList) {
            SnakeEntity snakePart = new SnakeEntity(0, 0);
            if (snakeList.indexOf(snakeEntity) == 0) {
                if (direction == up) {
                    snakePart.setxPos(snakeEntity.getXPos());
                    snakePart.setyPos(snakeEntity.getYPos() + 1);
                } else if (direction == down) {
                    snakePart.setxPos(snakeEntity.getXPos());
                    snakePart.setyPos(snakeEntity.getYPos() - 1);
                } else if (direction == left) {
                    snakePart.setxPos(snakeEntity.getXPos() - 1);
                    snakePart.setyPos(snakeEntity.getYPos());
                } else if (direction == right) {
                    snakePart.setxPos(snakeEntity.getXPos() + 1);
                    snakePart.setyPos(snakeEntity.getYPos());
                }
            } else {
                SnakeEntity prev = snakeList.get(snakeList.indexOf(snakeEntity) - 1);
                snakePart.setxPos(prev.getXPos());
                snakePart.setyPos(prev.getYPos());
            }
            newSnakeList.add(snakePart);
        }
        snakeList = newSnakeList;
        head = snakeList.get(0);
    }

    public SnakeEntity getHead() {
        return head;
    }

    public void draw() {
        for (SnakeEntity snakeEntity : snakeList) {
            snakeEntity.draw();
            if (snakeEntity.equals(head)) {

                if (direction == 0 || direction == 1) {
                    StdDraw.picture(snakeEntity.getXPos() * 0.04 + 0.02, snakeEntity.getYPos() * 0.04 + 0.02, "C:\\Users\\mjmik\\IdeaProjects\\project-mjmikejoo\\pictures\\snakeEyes.png", 0.05, 0.025);
                } else {
                    StdDraw.picture(snakeEntity.getXPos() * 0.04 + 0.02, snakeEntity.getYPos() * 0.04 + 0.02, "C:\\Users\\mjmik\\IdeaProjects\\project-mjmikejoo\\pictures\\snakeEyes.png", 0.05, 0.025, 90);
                }

            }
        }
    }

    public List<SnakeEntity> getSnakeList() {
        return snakeList;
    }

}
