module Model {
    requires org.apache.commons.lang3;
    requires java.sql;
    exports pl.comp.model;
    exports pl.comp.model.exceptions;
    opens pl.comp.model;
}