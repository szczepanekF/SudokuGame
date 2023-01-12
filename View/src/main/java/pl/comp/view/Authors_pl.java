package pl.comp.view;

import java.util.ListResourceBundle;

class Authors_pl extends ListResourceBundle {

    public Authors_pl() {
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1", "Miłosz Wojtaszczyk",},
                {"2", "Filip Szczepanek",},
        };
    }
}