package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public abstract class ImageActionBase implements Action {

	/**
	 * The image to manipulate
	 */
	protected Image image;
	
	/**
	 * The position of the image on the game board
	 */
	protected Cell cell;

	public ImageActionBase(Image image, Cell cell) {
		super();
		this.image = image;
		this.cell = cell;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cell).append(image).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof ImageActionBase)) {
			return false;
		}
		ImageActionBase other = (ImageActionBase) obj;
		return new EqualsBuilder().append(cell, other.cell).append(image, other.image).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageActionBase [cell=").append(cell).append(", image=").append(image).append("]");
		return builder.toString();
	}

}