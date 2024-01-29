package code;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    // Check the collision of the player
    public void checkTile(Player player){
        int playerLeftX = player.playerX;
        int playerRightX = player.playerX + player.solidArea.x;
        int playerTopY = player.playerY;
        int playerDownY = player.playerY + player.solidArea.y;

        int playerLeftCol = playerLeftX/gp.tilesScale;
        int playerRightCol = playerRightX/gp.tilesScale;
        int playerTopRow = playerTopY/gp.tilesScale;
        int playerDownRow = playerDownY/gp.tilesScale;

        // We need to check two tile before hand, not only one
        int tileNum1, tileNum2;

        switch (player.imgStat) {
            case "up":
                break;
            case "down":
                break;
            case "right":
                break;
            case "left":
                break;
            default:
                break;
        }
    }
}
