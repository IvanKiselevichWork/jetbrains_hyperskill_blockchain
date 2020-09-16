package by.kiselevich.temp10;

public class Main {
    public static void main(String[] args) {
//        Movable entity = null;
//        CommandMove moveCommand = new CommandMove(); // Testing system creates instance of CommandMove
//        moveCommand.entity = entity; // and initializes entity field by testing entity
//
//// then tested methods are invoked
//        moveCommand.execute();
//        moveCommand.undo();
    }
}

interface Movable {
    int getX();

    int getY();

    void setX(int newX);

    void setY(int newY);
}

interface Storable {
    int getInventoryLength();

    String getInventoryItem(int index);

    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();

    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;

    int oldXMovement;
    int oldYMovement;

    @Override
    public void execute() {
        oldXMovement = entity.getX();
        oldYMovement = entity.getY();
        entity.setX(xMovement);
        entity.setY(yMovement);
    }

    @Override
    public void undo() {
        entity.setX(oldXMovement);
        entity.setY(oldYMovement);
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;

    int index = -1;

    @Override
    public void execute() {
        int length = entity.getInventoryLength();
        for (int i = 0; i < length; i++) {
            if (entity.getInventoryItem(i) == null) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            entity.setInventoryItem(index, item);
        }
    }

    @Override
    public void undo() {
        if (index != -1) {
            entity.setInventoryItem(index, null);
        }
    }
}
