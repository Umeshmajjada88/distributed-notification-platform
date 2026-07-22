import { useQuery } from "@tanstack/react-query";
import { getDashboardData } from "../services/DashboardService";

export function useDashboard() {

    return useQuery({

        queryKey: ["dashboard"],

        queryFn: getDashboardData,

        staleTime: 30000,

        refetchOnWindowFocus: false

    });

}