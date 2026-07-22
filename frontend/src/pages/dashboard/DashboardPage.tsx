import { Typography } from "@mui/material";
import StatsGrid from "../../components/dashboard/StatsGrid";
import RecentNotificationsTable from "../../components/dashboard/RecentNotificationsTable";

export default function DashboardPage() {

    return (

        <>

            <Typography
                variant="h4"
                gutterBottom>

                Dashboard

            </Typography>

            <StatsGrid />

            <RecentNotificationsTable />

        </>

    );

}