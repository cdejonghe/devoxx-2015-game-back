package fr.sii.survival.core.service.board;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.board.BoardListenerRegistry;

public interface BoardService extends BoardListenerRegistry {
	/**
	 * Provide the list of players that are on the provided cell. If no player
	 * is on the cell, then the list is empty.
	 * 
	 * @param cell
	 *            the cell position
	 * @return the list of players on the cell
	 */
	public List<Player> getPlayers(Cell cell);

	/**
	 * Move a player to another cell. Checks if the player can move. If the
	 * player can move, then the returned cell points to the new location. If
	 * the player can't move, then the returned cell points to the previous
	 * location.
	 * 
	 * @param player
	 *            the player to move
	 * @param cell
	 *            the new cell position
	 * @return the new cell position if the player can move to it, the old cell
	 *         if the player can't move
	 */
	public Cell move(Player player, Cell cell);

	/**
	 * Adds a player on the board. Automatically selects a cell where the player
	 * will be placed.
	 * 
	 * @param player
	 *            the player to add
	 * @return the cell where the player is added
	 */
	public Cell add(Player player);

	/**
	 * Removes a player from the board.
	 * 
	 * @param player
	 *            the player to remove
	 * @return the cell where the player was before remove. If the player was
	 *         not on the board, then returns null
	 */
	public Cell remove(Player player);
	
	/**
	 * Search the position of the player in the game.
	 * 
	 * @param player
	 *            the player to look for
	 * @return the cell if the player is found, null otherwise
	 */
	public Cell getCell(Player player);
	
	/**
	 * Get the height of the board
	 * 
	 * @return the height of the board
	 */
	public int getHeight();

	/**
	 * Get the width of the board
	 * 
	 * @return the width of the board
	 */
	public int getWidth();

}