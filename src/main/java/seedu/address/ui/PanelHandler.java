package seedu.address.ui;

import java.util.HashMap;
import java.util.Optional;

import javafx.scene.Node;

import seedu.address.logic.Logic;
import seedu.address.logic.commands.CheckLimitCommand;
import seedu.address.logic.commands.DisplaymodCommand;
import seedu.address.logic.commands.DisplayreqCommand;
import seedu.address.logic.commands.RecCommand;
import seedu.address.logic.commands.SelectCommand;

/**
 * Handles which panels to display when the command changes
 */
public class PanelHandler {

    private HashMap<String, Node> panels = new HashMap<>();
    private Logic logic;

    public PanelHandler(Logic logic) {
        super();
        this.logic = logic;

        panels.put(DisplaymodCommand.COMMAND_WORD, new DisplayModuleInfoList(logic.getDisplayList(),
                logic.selectedModuleInfoProperty(),
                logic::setSelectedModuleInfo).getRoot());
        panels.put(DisplayreqCommand.COMMAND_WORD,
                new DisplayRequirementStatusList(logic.getRequirementStatusList()).getRoot());
        panels.put(RecCommand.COMMAND_WORD, new DisplayRecModuleList(logic.getRecModuleListSorted()).getRoot());
        panels.put(SelectCommand.COMMAND_WORD, new BrowserPanel(logic.selectedClassForPrintingProperty()).getRoot());
        panels.put(CheckLimitCommand.COMMAND_WORD, new BrowserPanel(
                logic.selectedClassForPrintingProperty()).getRoot());
    }

    public Optional<Node> getCommandPanel(String command) {
        if (command.equals(DisplayreqCommand.COMMAND_WORD)) {
            return Optional.of(new DisplayRequirementStatusList(logic.getRequirementStatusList()).getRoot());
        }
        return Optional.ofNullable(panels.get(command));
    }
}
