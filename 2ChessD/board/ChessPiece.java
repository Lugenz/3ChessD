package board;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ChessPiece {
    private PieceType piece;                        //enum
    private int x;                                  // x-coordinate of the piece on the board
    private int y;                                  // y-coordinate of the piece on the board
    private boolean color;
    private BufferedImage image;

    static final double UPSCALER = 1.2;             //scaling factor for 48x48 images
    /**
     * Constructs a ChessPiece with the specified position, type, and color.
     *
     * @param x the x-coordinate of the piece on the board
     * @param y the y-coordinate of the piece on the board
     * @param piece the type of the chess piece (enum PieceType)
     * @param color the color of the piece (true for white, false for black)
     */
    public ChessPiece (int x, int y, PieceType piece, boolean color) {
        this.x = x * 72;
        this.y = y * 72;
        this.color = color;
        this.piece = piece;
        this.image = this.loadImage(piece.getName(), color);                            
    }

    /**
     * Gets the x-coordinate of the chess piece on the board.
     *
     * @return the x-coordinate of the piece
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the chess piece on the board.
     *
     * @return the y-coordinate of the piece
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the color of the chess piece.
     *
     * @return true if the piece is white, false if the piece is black
     */
    public boolean getColor() {
        return this.color;
    }

    /**
     * Gets the size of the chess piece image after scaling.
     *
     * @return the size of the piece image
     */
    public int getSize() {
        return (int)(this.image.getWidth() * UPSCALER);
    }

    /**
     * Gets the type of the chess piece.
     *
     * @return the type of the piece (enum PieceType)
     */
    public PieceType getPiece() {
        return this.piece;
    }

    /**
     * Gets the image of the chess piece.
     *
     * @return the BufferedImage of the piece
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Sets the position of the chess piece on the board.
     *
     * @param x the new x-coordinate of the piece
     * @param y the new y-coordinate of the piece
     */
    public void setPosition(int x, int y) {
        this.x = x * 72;
        this.y = y * 72;
    }

    private BufferedImage loadImage(String piece, boolean color) {
        String path = color ? "assets/white/" : "assets/black/";
        try {
            return ImageIO.read(new File(path + piece + ".png"));
        } catch (Exception e) {
            System.out.println("Failed to load image for piece: " + piece);
            return null;
        }
    }

}
