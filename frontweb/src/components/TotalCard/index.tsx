// @ts-ignore
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { BASE_URL } from 'utils/requests';
import './styles.css';

const TotalCard = () => {
    const [total, setTotal] = useState<number | null>(null);

    useEffect(() => {
        const fetchTotal = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/transactions/total`);
                setTotal(response.data.sum);
            } catch (error) {
                console.error('Erro ao buscar total:', error);
                setTotal(null);
            }
        };

        fetchTotal();
        const interval = setInterval(fetchTotal, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div className="card total-card">
            <div className="card-body">
                <h5 className="card-title">Total</h5>
                <h2 className="card-value">
                    {total !== null ? `R$ ${total.toFixed(2)}` : 'Carregando...'}
                </h2>
            </div>
        </div>
    );
};

export default TotalCard; 