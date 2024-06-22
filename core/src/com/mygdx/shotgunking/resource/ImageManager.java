package com.mygdx.shotgunking.resource;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.*;

public class ImageManager {
    public static final ImageManager instance = new ImageManager();
    // img/piece/....png
    public final Texture pawnImg,rookImg,knightImg,bishopImg,queenImg,kingImg,blackKingImg;
    public final Texture boardImg;
    public final Texture backgroundImg;
    // img/icon/....png
    public final Texture ammo1,ammo2,blank,border,shadow,shield1,shield2,shootCursor;
    // img/card/....png
    public final Texture flipBlack,flipWhite;
    public final Texture pngAnBinhBatDong,pngBachPhatBachTrung,pngBatTotQuaDuong,pngBoSungQuanLuc,
        pngBuocNhayVanNang,pngCanBangSucManh,pngCanXung,pngChiaSeQuyenLuc,pngChienMa,pngChienXa,pngCuoiNguaHanhQuan,
        pngDanDuTru,pngDichBenh,pngDiemBanHoanHao,pngDongCamCongKho,pngHienTe,pngHieuTrieuBinhDoan,pngHoanHauSatThu,
        pngKeDiSan,pngKheUocQuyDu,pngLaChanThep,pngNgaiVangBoTrong,pngNgamNhinConMoi,pngNghiBinh,pngNguyenRua,pngNhapThanh,
        pngQuaDen,pngQuanSu,pngSongSinhDangSo,pngSucManhVoHan,pngSungVinhQuang,pngSuyGiamNhueKhi,pngTapChungCaoDo,pngTinhChinhSung,
        pngTuDo,pngUyQuyenQuanVuong,pngVuaSung,pngXaSung,pngXungPhong,pngVuaMau;
    // img/particle/....png
    public final Texture[] particle = new Texture[5];
    public final Texture[] blackParticle = new Texture[5];
    public final Texture shadowParticle;
    // img/number/....png
    public final Texture[] number = new Texture[10];
    // img/demo/...png
    public final Texture help1,help2,help3;
    private ImageManager(){
        backgroundImg = createImg("img/background.png");
        // 1
        {
            pawnImg = createImg("img/piece/pawn.png");
            rookImg = createImg("img/piece/rook.png");
            knightImg = createImg("img/piece/knight.png");
            bishopImg = createImg("img/piece/bishop.png");
            queenImg = createImg("img/piece/queen.png");
            kingImg = createImg("img/piece/king.png");
            blackKingImg = createImg("img/piece/blackking.png");
            boardImg = createImg("img/piece/board.png");
        }
        // 2
        {
            ammo1 = createImg("img/icon/ammo1.png");
            ammo2 = createImg("img/icon/ammo2.png");
            blank = createImg("img/icon/blank.png");
            border = createImg("img/icon/border.png");
            shadow = createImg("img/icon/shadow.png");
            shield1 = createImg("img/icon/shield1.png");
            shield2 = createImg("img/icon/shield2.png");
            shootCursor = createImg("img/icon/shootCursor.png");
        }
        // 3
        {
            flipBlack = createImg("img/card/flipBlack.png");
            flipWhite = createImg("img/card/flipWhite.png");
            pngAnBinhBatDong = createImg("img/card/AnBinhBatDong.png");
            pngBachPhatBachTrung = createImg("img/card/BachPhatBachTrung.png");
            pngBatTotQuaDuong = createImg("img/card/BatTotQuaDuong.png");
            pngBoSungQuanLuc = createImg("img/card/BoSungQuanLuc.png");
            pngBuocNhayVanNang = createImg("img/card/BuocNhayVanNang.png");
            pngCanBangSucManh = createImg("img/card/CanBangSucManh.png");
            pngCanXung = createImg("img/card/CanXung.png");
            pngChiaSeQuyenLuc = createImg("img/card/ChiaSeQuyenLuc.png");
            pngChienMa = createImg("img/card/ChienMa.png");
            pngChienXa = createImg("img/card/ChienXa.png");
            pngCuoiNguaHanhQuan = createImg("img/card/CuoiNguaHanhQuan.png");
            pngDanDuTru = createImg("img/card/DanDuTru.png");
            pngDichBenh = createImg("img/card/DichBenh.png");
            pngDiemBanHoanHao = createImg("img/card/DiemBanHoanHao.png");
            pngDongCamCongKho = createImg("img/card/DongCamCongKho.png");
            pngHienTe = createImg("img/card/HienTe.png");
            pngHieuTrieuBinhDoan = createImg("img/card/HieuTrieuBinhDoan.png");
            pngHoanHauSatThu = createImg("img/card/HoangHauSatThu.png");
            pngKeDiSan = createImg("img/card/KeDiSan.png");
            pngKheUocQuyDu = createImg("img/card/KheUocQuyDu.png");
            pngLaChanThep = createImg("img/card/LaChanThep.png");
            pngNgaiVangBoTrong = createImg("img/card/NgaiVangBoTrong.png");
            pngNgamNhinConMoi = createImg("img/card/NgamNhinConMoi.png");
            pngNghiBinh = createImg("img/card/NghiBinh.png");
            pngNguyenRua = createImg("img/card/NguyenRua.png");
            pngNhapThanh = createImg("img/card/NhapThanh.png");
            pngQuaDen = createImg("img/card/QuaDen.png");
            pngQuanSu = createImg("img/card/QuanSu.png");
            pngSongSinhDangSo = createImg("img/card/SongSinhDangSo.png");
            pngSucManhVoHan = createImg("img/card/SucManhVoHan.png");
            pngSungVinhQuang = createImg("img/card/SungVinhQuang.png");
            pngSuyGiamNhueKhi = createImg("img/card/SuyGiamNhueKhi.png");
            pngTapChungCaoDo = createImg("img/card/TapChungCaoDo.png");
            pngTinhChinhSung = createImg("img/card/TinhChinhSung.png");
            pngTuDo = createImg("img/card/TuDo.png");
            pngUyQuyenQuanVuong = createImg("img/card/UyQuyenQuanVuong.png");
            pngVuaSung = createImg("img/card/VuaSung.png");
            pngXaSung = createImg("img/card/XaSung.png");
            pngXungPhong = createImg("img/card/XungPhong.png");
            pngVuaMau = createImg("img/card/VuaMau.png");
        }
        // 4
        {
            for (int i = 0; i < 5; i++) {
                particle[i] = createImg("img/particle/particle" + i + ".png");
            }
            for (int i = 0; i < 5; i++) {
                blackParticle[i] = createImg("img/particle/bparticle" + i + ".png");
            }
            shadowParticle = createImg("img/particle/shadowParticle.png");
        }
        //5
        {
            for(int i=0;i<10;i++){
                number[i]= createImg("img/number/num"+i+".png");
            }
        }
        //6
        {
            help1=createImg("img/demo/help1.png");
            help2=createImg("img/demo/help2.png");
            help3=createImg("img/demo/help3.png");
        }
    }
    private static Texture createImg(String path){
        return new Texture(path);
    }
    public Texture getImgOfPiece(Piece piece){
        if(piece instanceof Pawn) return pawnImg;
        if (piece instanceof Knight) return knightImg;
        if (piece instanceof Bishop) return bishopImg;
        if (piece instanceof Rook) return rookImg;
        if (piece instanceof Queen) return queenImg;
        if (piece instanceof King) return kingImg;
        if (piece instanceof BlackKing) return blackKingImg;
        return pawnImg;
    }

}
