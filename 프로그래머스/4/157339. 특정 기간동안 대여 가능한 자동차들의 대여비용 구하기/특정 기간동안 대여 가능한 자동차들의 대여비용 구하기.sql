-- 코드를 입력하세요
-- 1. 세단 || SUV인  차중 30일간 대여 가능한 것 찾기
-- 2. Discount_rate 적용하여 금액 계산하기
-- 3. 30일간 대여 금액이 50만원에서 200만원 미만인 차량 찾기
-- 4. 출력하기 

WITH FEE_TABLE AS (
    SELECT
        A.CAR_ID,
        A.CAR_TYPE,
        FLOOR(A.DAILY_FEE * 30 * (1 - C.DISCOUNT_RATE / 100)) AS FEE
    FROM CAR_RENTAL_COMPANY_CAR A
    JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN C
        ON A.CAR_TYPE = C.CAR_TYPE
        AND C.DURATION_TYPE = '30일 이상'
    WHERE A.CAR_TYPE IN ('세단', 'SUV')
        AND NOT EXISTS (
            SELECT 1
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
            WHERE H.CAR_ID = A.CAR_ID
                AND H.START_DATE <= '2022-11-30'
                AND H.END_DATE >= '2022-11-01'
        )
)

SELECT *
FROM FEE_TABLE
WHERE FEE >= 500000 AND FEE < 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC

# SELECT 
#     A.CAR_ID,
#     A.CAR_TYPE,
#     FLOOR((A.daily_fee * (1 - (C.DISCOUNT_RATE / 100))) * 30) AS FEE
# FROM 
#     CAR_RENTAL_COMPANY_CAR AS A
# JOIN 
#     (SELECT CAR_ID, MIN(START_DATE) AS START_DATE, MAX(END_DATE) AS END_DATE
#     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#     GROUP BY CAR_ID
#     ) AS B
#     ON A.CAR_ID = B.CAR_ID
# JOIN 
#     (SELECT * 
#      FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
#      WHERE DURATION_TYPE = '30일 이상'
#     ) AS C
#     ON A.CAR_TYPE = C.CAR_TYPE
# WHERE NOT EXISTS (
#     (B.END_DATE < '2022-11-01' OR B.START_DATE > '2022-11-30')
#     AND
#     FLOOR((A.daily_fee * (1 - (C.DISCOUNT_RATE / 100))) * 30)  >= 500000
#     AND FLOOR((A.daily_fee * (1 - (C.DISCOUNT_RATE / 100))) * 30)  < 2000000
#     )
# ORDER BY
#     FEE DESC, A.CAR_TYPE ASC, A.CAR_ID DESC