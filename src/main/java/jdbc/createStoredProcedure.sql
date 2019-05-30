-- Create a trivial demo PostgreSQL stored procedure to find
-- products that are out of stock.

CREATE OR REPLACE FUNCTION findOutOfStock() RETURNS SETOF record AS $$
DECLARE
    fred RECORD;
BEGIN
	FOR fred in select sku,title FROM products where stockCount = 0 LOOP
		RETURN NEXT fred;
	END LOOP;
	return;
END
$$ LANGUAGE plpgsql;
