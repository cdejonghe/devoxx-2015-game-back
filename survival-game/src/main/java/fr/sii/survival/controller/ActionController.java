package fr.sii.survival.controller;

import static fr.sii.survival.config.ActionConfiguration.ACTION_MAPPING_PREFIX;
import static fr.sii.survival.config.WebSocketConfig.SERVER_PUBLISH_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.UpdatePosition;
import fr.sii.survival.core.domain.action.UpdateStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.action.StopAnimation;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.exception.PlayerNotFoundException;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.session.UserContext;

@Controller
public class ActionController extends ErrorController implements ActionListener {
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	ActionService actionService;
	
	@Autowired
	UserContext userContext;
	
	@Autowired
	GameService gameService;

	@MessageMapping(ACTION_MAPPING_PREFIX)
	public void execute(Action action) throws ActionException, GameNotFoundException, PlayerNotFoundException {
		Game game = gameService.getGame(userContext.getGameId());
		Player player = gameService.getPlayer(game, userContext.getPlayerId());
		actionService.execute(game, player, action);
	}
	
	@Override
	public void imageAdded(Game game, AddImage action) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/image/added", action);
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/image/moved", action);
	}

	@Override
	public void imageRemoved(Game game, RemoveImage action) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/image/removed", action);
	}

	@Override
	public void animationStarted(Game game, StartAnimation action) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/animation/started", action);
	}

	@Override
	public void animationStopped(Game game, StopAnimation action) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/animation/stopped", action);
	}
	
	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {
		// nothing to do: an event is already triggered when life of player has changed in PlayerController
	}

	@Override
	public void positionChanged(Game game, Player player, UpdatePosition action) {
		// nothing to do: an event is already triggered when position of player has changed in BoardController
		
	}

	@Override
	public void stateChanged(Game game, Player player, UpdateStates action) {
		// nothing to do: an event is already triggered when states of player has changed in PlayerController
	}

	
}
