import { AppBar, Box, Chip, Toolbar, Typography } from "@mui/material";

export default function Header() {

    return (

        <AppBar
            position="fixed"
            sx={{
                zIndex: 1300,
            }}
        >

            <Toolbar>

                <Typography
                    variant="h6"
                    sx={{ flexGrow: 1 }}
                >
                    Distributed Notification Platform
                </Typography>

                <Chip
                    color="success"
                    label="Connected"
                />

            </Toolbar>

        </AppBar>

    );

}