package board;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ChessPiece {
    private PieceType piece; //enum
    private int x;
    private int y;
    private int z;
    private boolean color;
    private BufferedImage image;
    

    static final double UPSCALER = 4.5;

    public ChessPiece (int x, int y, int z, PieceType piece, boolean color) {
        this.x = x*72;
        this.y = y*72;
        this.z = z*72;
        this.color = color;
        this.piece = piece;
        this.image = loadImage(piece.name, color);                            
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getZ() {
        return this.z;
    }
    public boolean getColor() {
        return this.color;
    }
    public int getSize() {
        return (int)(image.getWidth()*UPSCALER);
    }
    public PieceType getPiece() {
        return this.piece;
    }   
    
    public BufferedImage getImage() {
        return this.image;
    }

    public void setPosition(int x, int y, int z) {
        this.x = x*72;
        this.y = y*72;
        this.z = z*72;
    }

    private BufferedImage loadImage(String piece, boolean color) {
        String path = color ? "3ChessD/assets/white/" : "3ChessD/assets/black/";
        try {
            return ImageIO.read(new File(path + piece + ".png"));
        } catch (Exception e) {
            System.out.println("Failed to load image for piece: " + piece);
            return null;
        }
    }

    
}
