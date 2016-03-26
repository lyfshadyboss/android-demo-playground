package com.demo.lyf.foundation;

import java.util.ArrayList;

public class ResponseA extends BusinessBean {

    @SerializeField(type = SerializeType.Dynamic, length = 0, index = 1, require = true, serverType = "", format = "")
    public String resultMessage = "";

    @SerializeField(type = SerializeType.Dynamic, length = 0, index = 5, require = false, serverType = "", format = "")
    public String messageToken = "";

    @SerializeField(type = SerializeType.List, length = 0, index = 19, require = false, serverType = "FlightInlandOrderNoteInformation", format = "")
    public ArrayList<NoteInformationModel> orderNoteList = new ArrayList<NoteInformationModel>();

    public ResponseA() {
        super();
        super.realServiceCode = "10401002";
    }
}

