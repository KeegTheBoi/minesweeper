package controller;

import java.awt.Color;
import java.util.*;
import javax.swing.JButton;
import model.*;
import model.cells.*;
import view.*;
import static java.util.function.Predicate.not;

public class GUI implements Controller {

    private GameUI minerUI;
    private final Map<JButton, Coord> cells = new HashMap<>();
    private Logics logics;
    
    public GUI() {
        loadMenu();
    }

    @Override
    public void loadMenu() {
        final Menu men = new Menu(4, this);
        men.display();
    }

    @Override
    public void initGame() {
        final Level lv = minerUI.getStats();
        this.logics = new LogicsImpl(lv.size(), lv.difficulty());
        this.refresh();
    }

    @Override
    public void addButton(JButton jb, int x, int y) {
        cells.put(jb, new Coord(x, y));
    }

    @Override
    public void handleRightClick(JButton bt) {
        if (bt.isEnabled()){
            //call the logic here to put/remove a flag
            logics.flag(this.cells.get(bt));
        }
    }

    @Override
    public void refresh() {
        cells.forEach((k, v) -> {
            var cell = logics.getResult(v);
            if(!CellsUtils.isVeiled(cell) && CellsUtils.isValuable(cell)) {
                Color color = minerUI.getMapColor().get(cell.getCount().get());  //VIEW
                minerUI.modifyButton(k, this.getText(cell), color, false);
            } else if (cell.isFlagged()) {
                minerUI.modifyButton(k, "⚑", Color.RED, true);
            } else {
                minerUI.modifyButton(k, "", Color.BLACK, true);
            }
        });
    }

    @Override
    public void getGameLevel(FireButton bt) {
        this.minerUI = new GameUI(bt.getStats().size(), this, bt.getStats());
        this.minerUI.display();
        initGame();//review
    }

    @Override
    public void handleLeftClick(JButton bt) {
        final Coord pos = cells.get(bt);
        logics.hit(pos);
        boolean aMineWasFound = logics.isOver(); // call the logic here to tell it that cell at 'pos' has been seleced
        if (aMineWasFound) {
            quitGame();
            minerUI.closeWithMessage("You Lost");
        } else {
            this.refresh();            	
        }
        boolean isThereVictory = logics.hasWon(); // call the logic here to ask if there is victory
        if (isThereVictory){
            quitGame();
            minerUI.messageBox("You Won :)");
            this.initGame();

        }
    }

    //VIEW HAS TO DO IT
    private void quitGame() {
        this.refresh();
    	for (var entry: this.cells.entrySet()) {
            var cell = logics.getResult(entry.getValue());
            JButton btn = entry.getKey();
            Optional.of(cell).filter(CellsUtils::isBomb).ifPresent(c -> btn.setForeground(Color.yellow));
            minerUI.modifyButton(btn, this.getText(cell));
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
