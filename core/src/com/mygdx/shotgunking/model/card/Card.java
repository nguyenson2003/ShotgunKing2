package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class Card {

    private static List<Card> cardList = new ArrayList<>();
    private boolean flip;
    Card(){}

    /**
     * @return trả về tên của thẻ bài
     */
    abstract public String getName();

    /**
     * @return trả về nội dung của thẻ bài
     */
    abstract public String getDescription();

    /**
     * @return trả về true nếu đây là thẻ tăng sức mạnh cho vua đen,
     * false nếu đây là thẻ tăng sức mạnh cho quân trắng
     */
    abstract public boolean isBuffCard();

    abstract public Texture getTexture();

    public static Card randomABuffCard(){
        Card res;
        do {
            res = cardList.get((int) (cardList.size() * Math.random()));
        } while (!res.isBuffCard());
        return res;
    }
    public static Card randomADebuffCard(){
        Card res;
        do {
            res = cardList.get((int) (cardList.size() * Math.random()));
        } while (res.isBuffCard());
        return res;
    }

    static {
//        String packageName = Card.class.getPackage().getName();
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        URL packageURL;
//        packageURL = classLoader.getResource(packageName);
//        if (packageURL == null){
//            packageURL = classLoader.getResource(packageName.replaceAll("[.]","/"));
//        }
//        if (packageURL != null) {
//            String packagePath = packageURL.getPath();
//            if (packagePath != null) {
//                File packageDir = new File(packagePath);
//                if (packageDir.isDirectory()) {
//                    File[] files = packageDir.listFiles();
//                    for (File file : files) {
//                        String className = file.getName();
//                        if (className.endsWith(".class")) {
//                            className = packageName + "." + className.substring(0, className.length() - 6);
//                            try {
//                                Class<?> clazz = classLoader.loadClass(className);
//                                if(clazz.getSuperclass().equals(Card.class))
//                                    cardList.add((Card) clazz.getDeclaredConstructor().newInstance());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            // do something with the class
//                        }
//                    }
//                }
//            }
//        }
        cardList.add(new AnBinhBatDong());
        cardList.add(new BachPhatBachTrung());
        cardList.add(new BatTotQuaDuong());
        cardList.add(new BoSungQuanLuc());
        cardList.add(new BuocNhayVanNang());
        cardList.add(new CanBangSucManh());
        cardList.add(new CanXung());
        cardList.add(new ChiaSeQuyenLuc());
        cardList.add(new ChienMa());
        cardList.add(new ChienXa());
        cardList.add(new CuoiNguaHanhQuan());
        cardList.add(new DanDuTru());
        cardList.add(new DichBenh());
        cardList.add(new DiemBanHoanHao());
        cardList.add(new DongCamCongKho());
        cardList.add(new HienTe());
        cardList.add(new HieuTrieuBinhDoan());
        cardList.add(new HoangHauSatThu());
        cardList.add(new KeDiSan());
        cardList.add(new KheUocQuyDu());
        cardList.add(new LaChanThep());
        cardList.add(new NgaiVangBoTrong());
        cardList.add(new NgamNhinConMoi());
        cardList.add(new NghiBinh());
        cardList.add(new NguyenRua());
        cardList.add(new NhapThanh());
        cardList.add(new QuaDen());
        cardList.add(new QuanSu());
        cardList.add(new SongSinhDangSo());
        cardList.add(new SucManhVoHan());
        cardList.add(new SungVinhQuang());
        cardList.add(new SuyGiamNhueKhi());
        cardList.add(new TapChungCaoDo());
        cardList.add(new TinhChinhSung());
        cardList.add(new TuDo());
        cardList.add(new UyQuyenQuanVuong());
        cardList.add(new VuaMau());
        cardList.add(new VuaSung());
        cardList.add(new XaSung());
        cardList.add(new XungPhong());
        System.out.println("Số lượng thẻ:"+cardList.size());
    }

    /**
     * hàm sẽ được gọi trước khi tạo ra bàn cờ
     * @param gp gameplay truyền vào bao gồm cả bàn cờ và các thông tin của game
     */
    abstract public void actionBeforeInitBoard(Gameplay gp);
    abstract public void actionAfterInitBoard(Gameplay gp);
    abstract public void actionBeforeBlackAction(Gameplay gp);
    abstract public void actionAfterBlackAction(Gameplay gp);
    abstract public void actionAfterWhiteAction(Gameplay gp);
    abstract public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece);

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

}
