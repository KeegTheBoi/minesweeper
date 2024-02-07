package controller;

import model.*;
import model.cells.*;
import view.*;
import view.components.mybuttons.ViewButton;
import view.cores.game.GameView;

import java.util.*;

import javax.swing.JButton;

import static java.util.function.Predicate.not;

public class GameController<C> implements Controller{

    private static final String IMG_DIR = "/resource/";

    private final Map<ViewButton<C>, Coord> cells = new HashMap<>();
    private Logics logics;
    private GameView gameView;
    private Map<Integer, String> mapColor = new HashMap<>(Map.of(
         0, "#E8BCB9",
         1, "#F39F5A",
         2, "#AE445A",
         3, "#662549",
         4, "#CC00CC",
         5, "#800080"
    ));


    public GameController() {
    }


    @Override
    public void start(GameView view) {
        this.gameView = view;
        final Level lv = gameView.getLevel();//communicate between controllers
        this.logics = new LogicsImpl(lv.size(), lv.difficulty());
        this.refresh(false);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    public void addButton(ViewButton<C> jb, int x, int y) {
        cells.put(jb, new Coord(x, y));
    }

    public void handleRightClick(ViewButton<C> bt) {
        if (!bt.isDisable()){
            //call the logic here to put/remove a flag
            logics.flag(this.get(bt));
        }
    }
    //can improve
    public void refresh(boolean disable) {
        cells.forEach((k, v) -> {
            var cell = logics.getResult(v);
            String path = IMG_DIR;
            if(!CellsUtils.isVeiled(cell) && CellsUtils.isValuable(cell)) {
                String test = path.concat(cell.getCount().get() + ".png");
                k.setImagePath(test);
                k.setDisable(disable);
            } else if (cell.isFlagged()) {
                k.setImagePath(path.concat("flag" + ".png"));
                k.setDisable(false);
            } else {
                String test = path.concat("empty" + ".png");
                k.setImagePath(test);
                k.setDisable(false);
            }
            gameView.modifyButton(k);
            
        });
    }

    // @Override
    // public void getGameLevel(FireButton bt) {
    //     this.minerUI = new GameUI(bt.getStats().size(), this, bt.getStats());
    // }

    public void handleLeftClick(ViewButton<C> bt) {
        
        logics.hit(this.get(bt));
        boolean aMineWasFound = logics.isOver(); // call the logic here to tell it that cell at 'pos' has been seleced
        if (aMineWasFound) {
            quitGame();
            gameView.closeWithMessage("You Lost");
        } else {
            this.refresh(true);            	
        }
        boolean isThereVictory = logics.hasWon(); // call the logic here to ask if there is victory
        if (isThereVictory){
            quitGame();
            gameView.messageBox("You Won :)");
            this.reset();//mustimpl

        }
    }

    private Coord get(ViewButton<C> bt) {
        return cells.entrySet().stream().filter(y -> y.getKey().getId() == bt.getId()).findAny().get().getValue();
    }


    //VIEW HAS TO DO IT
    private void quitGame() {
        this.refresh(false);
    	for (var entry: this.cells.entrySet()) {
            var cell = logics.getResult(entry.getValue());
            ViewButton<C> btn = entry.getKey();
            Optional.of(cell).filter(CellsUtils::isBomb).ifPresent(c ->{
                btn.setFGColor("#B5C99A");
                btn.setBGColor("#1D1A39");
                btn.setImagePath(IMG_DIR.concat("flag" + ".png"));
            } );
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
