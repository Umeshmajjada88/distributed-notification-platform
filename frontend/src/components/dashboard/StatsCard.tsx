import {
    Card,
    CardContent,
    CircularProgress,
    Typography
} from "@mui/material";

interface Props {

    title: string;

    value?: number;

    loading?: boolean;

}

export default function StatsCard({

    title,

    value,

    loading = false

}: Props) {

    return (

        <Card elevation={3}>

            <CardContent>

                <Typography
                    color="text.secondary"
                    gutterBottom>

                    {title}

                </Typography>

                {

                    loading

                        ?

                        <CircularProgress size={28}/>

                        :

                        <Typography variant="h4">

                            {value ?? 0}

                        </Typography>

                }

            </CardContent>

        </Card>

    );

}