package com.mygdx.shotgunking.model;


import com.mygdx.shotgunking.view.gameplay.GameplayRoom;

public class BlackKing extends Piece {
    //shield: khiên
    int shield, maxShield;
    //tọa độ x,y các ô xung quanh vua
    int aroundX[]={-1,0,1,-1,1,-1,0,1};
    int aroundY[]={-1,-1,-1,0,0,1,1,1};
    //shellAmmo: đạn trong súng, spareAmmo: đạn dự phòng
    int shellAmmo, maxShellAmmo, spareAmmo, maxSpareAmmo;

    public void setShellAmmo(int shellAmmo) {
        this.shellAmmo = shellAmmo;
    }

    public void setSpareAmmo(int spareAmmo) {
        this.spareAmmo = spareAmmo;
    }

    public void setMaxShellAmmo(int maxShellAmmo) {
        this.maxShellAmmo = maxShellAmmo;
    }

    public void setMaxSpareAmmo(int maxSpareAmmo) {
        this.maxSpareAmmo = maxSpareAmmo;
    }
     /**
     * @return true nến Tile c trong phạm vi của ủy quyền quân vương và ngược lại
     */
    public boolean checkUyQuyenQuanVuong(Tile c){
        return Math.abs(standing.x-c.x)<=1&&Math.abs(standing.y-c.y)<=1;
    }
    //firePower: hỏa lực|số sát thương trong 1 lần bắn (chia đều các con trong vùng)
    //fireRange: tầm bắn +-1
    //spread: góc lệch tính theo radian
    int firePower, fireRange;
    double spread;

    double oldAngle;

    BlackKing(
            Tile standing,
            Board onBoard,
            int maxShield,
            int maxShellAmmo,
            int maxSpareAmmo,
            int firePower,
            int fireRange,
            int spread //tính theo degree
    ) {
        super(standing, onBoard);
        this.shield = this.maxShield = maxShield;
        this.shellAmmo = this.maxShellAmmo = maxShellAmmo;
        this.spareAmmo = this.maxSpareAmmo = maxSpareAmmo;
        this.firePower = firePower;
        this.fireRange = fireRange;
        this.spread = Math.toRadians(spread);
    }

    public void reloadAmmo() {
        if (shellAmmo < maxShellAmmo && spareAmmo > 0) {
            int waitAmmo = Math.min(spareAmmo, maxShellAmmo - shellAmmo);
            shellAmmo += waitAmmo;
            spareAmmo -= waitAmmo;
        } else if (spareAmmo < maxSpareAmmo) {
            spareAmmo++;
        }
    }

    /**
     * @param nextMove nước đi tiếp theo của quân vua
     * @return trả về true nếu nước đi đó hợp lệ, false ngược lại
     */
    public boolean canMoveTo(Tile nextMove) {
        if (onBoard.getPiece(nextMove) != null) return false;
        int kc = 1;
        if (onBoard.dataBuff.isBuocNhayVanNang) {
            kc = 2;
        }
        if (Math.abs(nextMove.x - standing.x) <= kc && Math.abs(nextMove.y - standing.y) <= kc)
            return true;
        else
            return false;
    }

    /**
     * Hành động di chuyển của quân vua
     *
     * @param nextMove
     * @throws IllegalArgumentException không phải nước đi hợp lệ
     */
    public void move(Tile nextMove) {
        if (onBoard.getPiece(nextMove) != null)
            throw new IllegalArgumentException(
                    "Ô "+nextMove.toString()+" đã có quân cờ"
            );
        if (!canMoveTo(nextMove))
            throw new IllegalArgumentException("quan co ko the di den o nay");
        int absx = Math.abs(nextMove.x - standing.x), absy = Math.abs(nextMove.y - standing.y);
        if (absx + absy >= 2 && absx * absy != 1) {
            onBoard.dataBuff.isBuocNhayVanNang = false;
        }
        standing = nextMove;

        reloadAmmo();
    }

    /**
     * Kiểm tra vua có thể bắn hay ko
     *
     * @return true nếu vua còn đạn trong hộp đạn dể bắn
     */
    public boolean isCanShoot() {
        return shellAmmo > 0;
    }

    /**
     * Hành động bắn của quân vua
     *
     * @param angle góc tính theo radian
     */
    public void shoot(double angle) {
        if (shellAmmo < 0) return;
        oldAngle = angle;
        shellAmmo--;
        loopBullet:
        for (int i = 0; i < firePower; i++) {
            double standingX = standing.x + 0.5;
            double standingY = standing.y + 0.5;
            double rangeOfBullet = fireRange + 1 - Math.random() * 2;
            double bulletAngle = angle + spread / 2 - Math.random() * spread;
            double endBulletX = 0;
            double endBulletY = 0;
            for (double r = 0.5; r < rangeOfBullet; r += 0.1) {
                double bulletX = standingX + r * Math.cos(bulletAngle);
                double bulletY = standingY + r * Math.sin(bulletAngle);
                endBulletX = bulletX;
                endBulletY = bulletY;
                if (Tile.isOnBoard((int) bulletX, (int) bulletY)) {
                    Tile t = new Tile((int) bulletX, (int) bulletY);
                    Piece p = onBoard.getPiece(t);
                    if (p instanceof WhitePiece ) {
                        WhitePiece wp = (WhitePiece) p;
                        if(!wp.isDied()) {
                            if (onBoard.dataBuff.isLaChanThep && wp instanceof King && wp.getHp() == 1) {
                                if (!onBoard.isHasBishopOnBoard)
                                    wp.takeDamage();
                                break;
                            }
                            wp.takeDamage();
                            break;
                        }
                    }
                } else break;
            }
            GameplayRoom.getIns().getBoardView().drawABullet(standingX, standingY, endBulletX, endBulletY);
        }
    }


    @Override
    char getSymbol() {
        return 'k';
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }

    public void setSpread(double spread) {
        this.spread = spread;
    }

    public double getOldAngle() {
        return oldAngle;
    }

    public int getShield() {
        return shield;
    }

    public int getMaxShield() {
        return maxShield;
    }

    public int getShellAmmo() {
        return shellAmmo;
    }

    public int getMaxShellAmmo() {
        return maxShellAmmo;
    }

    public int getSpareAmmo() {
        return spareAmmo;
    }

    public int getMaxSpareAmmo() {
        return maxSpareAmmo;
    }

    public int getFirePower() {
        return firePower;
    }

    public int getFireRange() {
        return fireRange;
    }

    public double getSpread() {
        return spread;
    }
}
