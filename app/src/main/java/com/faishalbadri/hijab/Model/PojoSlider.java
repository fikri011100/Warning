package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by faishal on 9/14/17.
 */

public class PojoSlider {

    /**
     * slider : [{"id_slider":"1","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"1"},{"id_slider":"2","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"0"},{"id_slider":"3","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"0"},{"id_slider":"4","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"0"},{"id_slider":"5","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"0"},{"id_slider":"6","slider_judul":"example","slider_gambar":"example.png","slider_keterangan":"example","slider_view":"0"},{"id_slider":"7","slider_judul":"sadq1we","slider_gambar":"example.png","slider_keterangan":"\nasd12<\/p>\r\n","slider_view":"0"}]
     * status : 1
     * msg : Data Semua Slider
     */

    private String status;
    private String msg;
    private List<SliderBean> slider;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SliderBean> getSlider() {
        return slider;
    }

    public void setSlider(List<SliderBean> slider) {
        this.slider = slider;
    }

    public static class SliderBean {
        /**
         * id_slider : 1
         * slider_judul : example
         * slider_gambar : example.png
         * slider_keterangan : example
         * slider_view : 1
         */

        private String id_slider;
        private String slider_judul;
        private String slider_gambar;
        private String slider_keterangan;
        private Double slider_view;

        public String getId_slider() {
            return id_slider;
        }

        public void setId_slider(String id_slider) {
            this.id_slider = id_slider;
        }

        public String getSlider_judul() {
            return slider_judul;
        }

        public void setSlider_judul(String slider_judul) {
            this.slider_judul = slider_judul;
        }

        public String getSlider_gambar() {
            return slider_gambar;
        }

        public void setSlider_gambar(String slider_gambar) {
            this.slider_gambar = slider_gambar;
        }

        public String getSlider_keterangan() {
            return slider_keterangan;
        }

        public void setSlider_keterangan(String slider_keterangan) {
            this.slider_keterangan = slider_keterangan;
        }

        public Double getSlider_view() {
            return slider_view;
        }

        public void setSlider_view(Double slider_view) {
            this.slider_view = slider_view;
        }
    }
}
