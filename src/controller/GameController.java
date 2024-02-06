package controller;

import model.*;
import model.cells.*;
import view.*;
import view.components.mybuttons.Button;
import view.cores.*;
import view.cores.game.GameView;

import java.util.*;

import static java.util.function.Predicate.not;

public class GameController<C> implements Controller{

    private final Map<Button<C>, Coord> cells = new HashMap<>();
    private Logics logics;
    private GameView gameView;
    private Map<Integer, String> mapColor = new HashMap<>(Map.of(
         0, "#333333",
         1, "#006400",
         2, "#8B0000",
         3, "#0000FF",
         4, "#CC00CC",
         5, "#800080"
    ));


    public GameController(GameView view) {
        this.gameView = view;
    }


    @Override
    public void start() {
        final Level lv = new Level(10, 5);//communicate between controllers
        this.logics = new LogicsImpl(lv.size(), lv.difficulty());
        this.refresh();
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    public void addButton(Button<C> jb, int x, int y) {
        cells.put(jb, new Coord(x, y));
    }

    public void handleRightClick(Button<C> bt) {
        if (!bt.isDisable()){
            //call the logic here to put/remove a flag
            logics.flag(this.get(bt));
        }
    }

    public void refresh() {
        cells.forEach((k, v) -> {
            var cell = logics.getResult(v);
            if(!CellsUtils.isVeiled(cell) && CellsUtils.isValuable(cell)) {
                String color = mapColor.get(cell.getCount().get());  //VIEW
                k.setBGColor(color);
                k.setText(getText(cell));
                k.setDisable(true);
            } else if (cell.isFlagged()) {
                k.setText("⚑");
                k.setBGColor("#FF0000");
                k.setDisable(false);
            } else {
                k.setText("");
                k.setBGColor("#000000");
                k.setDisable(false);
            }
            gameView.modifyButton(k);
        });
    }

    // @Override
    // public void getGameLevel(FireButton bt) {
    //     this.minerUI = new GameUI(bt.getStats().size(), this, bt.getStats());
    // }

    public void handleLeftClick(Button<C> bt) {
        
        logics.hit(this.get(bt));
        boolean aMineWasFound = logics.isOver(); // call the logic here to tell it that cell at 'pos' has been seleced
        if (aMineWasFound) {
            quitGame();
            gameView.closeWithMessage("You Lost");
        } else {
            this.refresh();            	
        }
        boolean isThereVictory = logics.hasWon(); // call the logic here to ask if there is victory
        if (isThereVictory){
            quitGame();
            gameView.messageBox("You Won :)");
            this.reset();

        }
    }

    private Coord get(Button<C> bt) {
        return cells.entrySet().stream().filter(y -> y.getKey().getId() == bt.getId()).findAny().get().getValue();
    }


    //VIEW HAS TO DO IT
    private void quitGame() {
        this.refresh();
    	for (var entry: this.cells.entrySet()) {
            var cell = logics.getResult(entry.getValue());
            Button<C> btn = entry.getKey();
            Optional.of(cell).filter(CellsUtils::isBomb).ifPresent(System.out::println);
            btn.setText(this.getText(cell));
            gameView.modifyButton(btn);
    	}
    }

    private String getText(Cell cell) {
        switch (cell.getType()) {
            case MINE:
                return "💣";
            case GROUND:
                return Optional.of(cell).filter(not(CellsUtils::isEmpty)).map(Cell::getCount)
                    .map(Optional::get).map(String::valueOf).orElse("");
            default:
                return "";
        }
    }
    
}
