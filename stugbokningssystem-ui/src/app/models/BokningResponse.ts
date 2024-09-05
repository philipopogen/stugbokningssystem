import { StugaResponse } from "./StugaResponse";

export interface BokningResponse{
    id: number;
  stugaId: number;
  incheckningsdatum: string; 
  utcheckningsdatum: string;
  turistNamn: string;
  turistEpost: string;
  turistMobilnummer: string;
  turistAdress: string;
  stugaEntity:StugaResponse
}