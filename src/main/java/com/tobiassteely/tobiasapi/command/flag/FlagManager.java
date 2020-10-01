package com.tobiassteely.tobiasapi.command.flag;

import com.tobiassteely.tobiasapi.api.manager.ManagerEventExecutor;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;

public class FlagManager extends ManagerParent<CommandFlag> {

    public FlagManager() {
        super("XDC.FlagManager", true);
    }

}
