package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

public class PlayerListenerAdapter implements PlayerListener {

	@Override
	public void dead(Player player) {

	}

	@Override
	public void revived(Player player) {

	}

	@Override
	public void hit(Player player, int damage) {

	}

	@Override
	public void healed(Player player, int amount) {

	}

	@Override
	public void statesChanged(Player player, List<StateChange> changes) {
		
	}

	@Override
	public void maxLifeChanged(Player player, int amount) {
		
	}

}
