package pl.comp.view;

import java.util.ListResourceBundle;

class Authors_en extends ListResourceBundle {

    public Authors_en() {
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1", "Filip Szczepanek",},
                {"2", "Miłosz Wojtaszczyk",},
        };
    }
}
