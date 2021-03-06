package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.UpdateCurrentLife;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.service.action.ActionService;

public class HealActionBehavior extends SimpleActionBehavior {

	private int amount;

	public HealActionBehavior(ActionService actionService, Enemy enemy, int amount) {
		super(actionService, enemy);
		this.amount = amount;
	}

	@Override
	public void execute(Game game, Cell cell) throws ActionException {
		actionService.execute(game, enemy, new UpdateCurrentLife(amount, cell));
	}

}
