import DashboardIcon from "@mui/icons-material/Dashboard";
import NotificationsIcon from "@mui/icons-material/Notifications";
import ErrorIcon from "@mui/icons-material/Error";
import SettingsIcon from "@mui/icons-material/Settings";

import {
    Drawer,
    List,
    ListItemButton,
    ListItemIcon,
    ListItemText,
    Toolbar,
} from "@mui/material";
import { NavLink } from "react-router-dom";

const drawerWidth = 240;

export default function Sidebar() {

    return (

        <Drawer
            variant="permanent"
            sx={{
                width: drawerWidth,
                flexShrink: 0,
                "& .MuiDrawer-paper": {
                    width: drawerWidth,
                    boxSizing: "border-box",
                },
            }}
        >

            <Toolbar />

            <List>

                <ListItemButton
                    component={NavLink}
                    to="/"
                >

                    <ListItemIcon>

                        <DashboardIcon />

                    </ListItemIcon>

                    <ListItemText primary="Dashboard" />

                </ListItemButton>

                <ListItemButton
                    component={NavLink}
                    to="/notifications"
                >

                    <ListItemIcon>

                        <NotificationsIcon />

                    </ListItemIcon>

                    <ListItemText primary="Notifications" />

                </ListItemButton>

                <ListItemButton
                    component={NavLink}
                    to="/deadletters"
                >

                    <ListItemIcon>

                        <ErrorIcon />

                    </ListItemIcon>

                    <ListItemText primary="Dead Letters" />

                </ListItemButton>

                <ListItemButton
                    component={NavLink}
                    to="/settings"
                >

                    <ListItemIcon>

                        <SettingsIcon />

                    </ListItemIcon>

                    <ListItemText primary="Settings" />

                </ListItemButton>

            </List>

        </Drawer>

    );

}