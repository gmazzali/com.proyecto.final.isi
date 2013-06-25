package com.proyecto.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Resources {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 15;
	private static Integer width = 15;

	/**
	 * El icono para "nueva entidad".
	 */
	public static ImageIcon ADD_ICON;
	/**
	 * El icono para "modificar entidad".
	 */
	public static ImageIcon MODIFY_ICON;
	/**
	 * El icono para "eliminar entidad".
	 */
	public static ImageIcon DELETE_ICON;
	/**
	 * El icono para retornar.
	 */
	public static ImageIcon RETURN_ICON;

	/**
	 * Los archivos de los gif de progresos.
	 */
	public static ImageIcon PROGRESS_LIST_ICON;
	public static ImageIcon PROGRESS_SAVE_ICON;
	public static ImageIcon PROGRESS_BAR_ICON;

	static {
		Resources.ADD_ICON = new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/add.png")).getImage()
				.getScaledInstance(Resources.width, Resources.height, Image.SCALE_SMOOTH));

		Resources.MODIFY_ICON = new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/modify.png")).getImage()
				.getScaledInstance(Resources.width, Resources.height, Image.SCALE_SMOOTH));

		Resources.DELETE_ICON = new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/delete.png")).getImage()
				.getScaledInstance(Resources.width, Resources.height, Image.SCALE_SMOOTH));

		Resources.RETURN_ICON = new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/return.png")).getImage()
				.getScaledInstance(Resources.width, Resources.height, Image.SCALE_SMOOTH));

		Resources.PROGRESS_LIST_ICON = new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/progress_list.gif"));

		Resources.PROGRESS_SAVE_ICON = new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/progress_save.gif"));

		Resources.PROGRESS_BAR_ICON = new ImageIcon(Resources.class.getClassLoader().getResource("com/proyecto/image/progress_bar.gif"));
	}
}
