package com.mygdx.shotgunking.view.help;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.home.HomeRoom;
import com.mygdx.shotgunking.view.shared.*;

public class HelpRoom extends TRoom {
    int page;
    TPanel titleLabel = new TPanel("Trợ giúp");
    TBorderLayoutPanel northPanel = new TBorderLayoutPanel();
    TPanel centerPanel = new TBorderLayoutPanel();
    TPanel contentPanel = new TPanel();
    TPanel imgPanel = new TPanel();
    TBorderLayoutPanel southPanel = new TBorderLayoutPanel();
    TButton homeButton = new TButton("Màn hình chính"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            ShotgunKing.instance.setRoom(new HomeRoom());
            return true;
        }
    };
    TButton backButton = new TButton("Trước"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            if(page>1)
                ShotgunKing.instance.setRoom(new HelpRoom(page-1));
            return true;
        }
    };
    TButton nextButton = new TButton("Tiếp"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            if(page<3)
                ShotgunKing.instance.setRoom(new HelpRoom(page+1));
            return true;
        }
    };

    public HelpRoom(int page){
        this.page=page;
        this.add(northPanel, NORTH);
        this.add(centerPanel, CENTER);
        this.add(southPanel, SOUTH);
        northPanel.add(titleLabel);
        centerPanel.add(contentPanel);
        centerPanel.add(imgPanel,EAST);
        contentPanel.setTextAlign(Align.topLeft);
        contentPanel.setTextWarp(true);
        if(page==1) {
            contentPanel.setText(
                    "   Bạn sẽ được trong vai của quân vua đen cầm theo một khẩu súng shotgun và bị quân đội của mình phản bội về phe trắng\n" +
                    "   Bàn cờ 8x8 có các ô trắng và ô đen giống như một bàn cờ vua bình thường. Bên đen chi có một con vua, còn bên trắng có rất nhiều loại quân cờ. Tùy theo quá trình chơi trò chơi, số lượng của các quân cờ phe địch sẽ bị thay đổi (ví dụ phe địch có thể có 3 quân mã và không có quân hậu nào)\n");
            backButton.setVisible(false);
            imgPanel.setImg(ImageManager.instance.help1);
        }else if(page==2){
            contentPanel.setText(
                    "   Mỗi luợt chơi sẽ được diễn ra như sau:\n" +
                    "    -   Di chuyển quân vua của mình sang 8 ô xung quanh hoặc bắn vào quân địch. Nếu hết đạn để bắn, bạn phải di chuyển để nạp đạn.\n" +
                    "    -   Quân trắng sẽ di chuyển giống theo quy tắc của cờ vua để tấn công mình.\n");
            imgPanel.setImg(ImageManager.instance.help2);
        }else if(page==3){
            contentPanel.setText(
                    "   Tránh chiếu tướng và tiêu diệt vua địch để hoàn thành trận đấu. \n" +
                    "   Sau mỗi màn, bạn có thể chọn giữa hai kết hợp ngẫu nhiên, một nâng cấp cho bạn và một nâng cấp cho bên kia, hãy lựa chọn một cách khôn ngoan và tiếp tục chiến thắng các màn và bạn có thể lấy lại vương quốc của mình\n");
            nextButton.setVisible(false);
            imgPanel.setImg(ImageManager.instance.help3);
        }else {
            throw new RuntimeException();
        }

        southPanel.add(backButton,WEST);
        southPanel.add(nextButton,EAST);
        southPanel.add(homeButton);
//        centerPanel.add(exitButton);
//        AudioManager.instance.playMusic(AudioManager.instance.backgroundMusic);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        northPanel.setSize(this.getWidth(),this.getHeight()/5);
        titleLabel.setTextScale(this.getHeight()*0.2f/100);
        contentPanel.setTextScale(this.getHeight()*0.1f/100);
        centerPanel.setBorder(new TBorder(new Color(0),0,getWidth()/20,getHeight()/20,getWidth()/20));
        southPanel.setBorder(new TBorder(new Color(0),0,getWidth()/20,getHeight()/20,getWidth()/20));
        southPanel.setSize(this.getWidth(),this.getHeight()/6);
        southPanel.setHgap(this.getWidth()/100);
        float fontBtnScale = this.getHeight()*0.1f/100;
        homeButton.setTextScale(fontBtnScale);
        backButton.setTextScale(fontBtnScale);
        nextButton.setTextScale(fontBtnScale);
        homeButton.setSize(this.getWidth()/4,this.getHeight());
        backButton.setSize(this.getWidth()/4,this.getHeight());
        nextButton.setSize(this.getWidth()/4,this.getHeight());
        imgPanel.setSize(this.getWidth()/3,this.getHeight());
    }

}
