package fr.sii.survival.core.listener.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.BoardListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

public class SimpleBoardListenerManager implements BoardListenerManager {

	private Map<String, BoardListener> listeners;
	
	private MessageService errorService;
	
	private ExtensionService extensionService;
	
	public SimpleBoardListenerManager(MessageService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimpleBoardListenerManager(Map<String, BoardListener> listeners, MessageService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addBoardListener(BoardListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Board listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeBoardListener(BoardListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerMoved(Game game, Player player, Cell oldCell, Cell newCell) {
		for(BoardListener listener : getListeners()) {
			try {
				listener.moved(game, player, oldCell, newCell);
			} catch(Exception e) {
				errorService.addError(new BoardListenerException("failed to trigger moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerAdded(Game game, Player player, Cell cell) {
		for(BoardListener listener : getListeners()) {
			try {
				listener.added(game, player, cell);
			} catch(Exception e) {
				errorService.addError(new BoardListenerException("failed to trigger added event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerRemoved(Game game, Player player, Cell cell) {
		for(BoardListener listener : getListeners()) {
			try {
				listener.removed(game, player, cell);
			} catch(Exception e) {
				errorService.addError(new BoardListenerException("failed to trigger removed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	private List<BoardListener> getListeners() {
		return new CopyOnWriteArrayList<>(listeners.values());
	}
	
	private String getKey(BoardListener listener) {
		return listener.getClass().toString();
	}
}
