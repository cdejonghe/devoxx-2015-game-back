package fr.sii.survival.core.ext.behavior.action;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

/**
 * Decorator that executes several delegate behaviors
 * 
 * @author Aurélien Baudet
 *
 */
public class MultiActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(MultiActionBehavior.class);

	private List<EnemyActionBehavior> actions;

	public MultiActionBehavior(List<EnemyActionBehavior> actions) {
		super();
		this.actions = actions;
	}

	public MultiActionBehavior(EnemyActionBehavior... actions) {
		this(Arrays.asList(actions));
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		LOG.debug("executing several actions {} for game {} on cell {}", actions, game, cell);
		for (EnemyActionBehavior action : actions) {
			action.execute(game, cell);
		}
	}

}
