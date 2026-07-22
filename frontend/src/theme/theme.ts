import { createTheme } from "@mui/material/styles";

const theme = createTheme({

    palette: {

        mode: "light",

        primary: {

            main: "#1976d2",

        },

        secondary: {

            main: "#2e7d32",

        },

        background: {

            default: "#f5f7fa",

        },

    },

    typography: {

        fontFamily: "Inter, Roboto, Arial",

    },

});

export default theme;