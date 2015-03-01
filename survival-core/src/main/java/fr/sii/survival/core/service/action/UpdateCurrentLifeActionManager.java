package fr.sii.survival.core.service.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Action manager that is able to handle current life update.
 * 
 * @author aurelien
 *
 */
public class UpdateCurrentLifeActionManager implements ActionManager<UpdateCurrentLife> {
	private static Logger logger = LoggerFactory.getLogger(UpdateCurrentLifeActionManager.class);

	private BoardService boardService;
	
	private PlayerService playerService;
	
	private ActionListenerTrigger actionListenerTrigger;
	
	public UpdateCurrentLifeActionManager(BoardService boardService, PlayerService playerService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.boardService = boardService;
		this.playerService = playerService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof UpdateCurrentLife;
	}

	@Override
	public void execute(UpdateCurrentLife action) {
		logger.info("update current life {} on {}", action.getIncrement(), action.getCell());
		List<Player> players = boardService.getPlayers(action.getCell());
		for(Player player : players) {
			int inc = playerService.updateCurrentLife(player, action.getIncrement());
			actionListenerTrigger.triggerLifeUpdated(player, new UpdateCurrentLife(inc, action.getCell()));
		}
	}

}