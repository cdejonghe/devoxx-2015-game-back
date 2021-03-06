package fr.sii.survival.core.domain.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;

/**
 * Action that enable to update several states for players that are available on
 * the cell
 * 
 * @author Aurélien Baudet
 *
 */
public class UpdateStates implements Action {
	/**
	 * The cell to target
	 */
	private Cell cell;

	/**
	 * A list of changes to apply. Each change provide the name of the state and
	 * the action to apply on the provided state (add or remove)
	 */
	private List<StateChange> stateChanges;

	public UpdateStates(UpdateStates action) {
		this(action.getCell(), new ArrayList<>(action.getStateChanges()));
	}

	public UpdateStates(Cell cell, StateChange... changes) {
		this(cell, new ArrayList<>(Arrays.asList(changes)));
	}

	public UpdateStates(Cell cell, List<StateChange> stateChanges) {
		super();
		this.cell = cell;
		this.stateChanges = stateChanges;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public List<StateChange> getStateChanges() {
		return stateChanges;
	}

	public void setStateChanges(List<StateChange> stateChanges) {
		this.stateChanges = stateChanges;
	}

	public void addStateChange(StateChange stateChange) {
		stateChanges.add(stateChange);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cell).append(stateChanges).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof UpdateStates)) {
			return false;
		}
		UpdateStates other = (UpdateStates) obj;
		return new EqualsBuilder().append(cell, other.cell).append(stateChanges, other.stateChanges).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeStates [cell=").append(cell).append(", stateChanges=").append(stateChanges).append("]");
		return builder.toString();
	}
}
