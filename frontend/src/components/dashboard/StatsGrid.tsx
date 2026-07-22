import Grid from "@mui/material/Grid";
import StatsCard from "./StatsCard";
import { useDashboard } from "../../hooks/useDashboard";

export default function StatsGrid() {

    const {

        data,

        isLoading,

        error

    } = useDashboard();

    if (error) {

        return <>Unable to load dashboard.</>;

    }

    return (

        <Grid container spacing={3}>

            <Grid size={{ xs: 12, md: 3 }}>

                <StatsCard

                    title="Notifications"

                    value={data?.notifications.total}

                    loading={isLoading}

                />

            </Grid>

            <Grid size={{ xs: 12, md: 3 }}>

                <StatsCard

                    title="Delivered"

                    value={data?.deliveries.delivered}

                    loading={isLoading}

                />

            </Grid>

            <Grid size={{ xs: 12, md: 3 }}>

                <StatsCard

                    title="Retry Pending"

                    value={data?.deliveries.retryPending}

                    loading={isLoading}

                />

            </Grid>

            <Grid size={{ xs: 12, md: 3 }}>

                <StatsCard

                    title="Dead Letters"

                    value={data?.deadLetters.total}

                    loading={isLoading}

                />

            </Grid>

        </Grid>

    );

}