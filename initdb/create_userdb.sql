
create user 'cinshop_user'@'%' identified by "cinshopuser";

create user 'cinshop_admin'@'%' identified by "cinshopadmin";

grant all privileges on cinshopdb.* to 'cinshop_admin'@'%';

grant select on cinshopdb.brand to 'cinshop_user'@'%';
grant select on cinshopdb.category to 'cinshop_user'@'%';
grant select on cinshopdb.color to 'cinshop_user'@'%';
grant select on cinshopdb.size to 'cinshop_user'@'%';
grant select on cinshopdb.tax to 'cinshop_user'@'%';
grant select on cinshopdb.payment to 'cinshop_user'@'%';
grant select on cinshopdb.sex to 'cinshop_user'@'%';

grant select on cinshopdb.product to 'cinshop_user'@'%';
grant select on cinshopdb.product_detail to 'cinshop_user'@'%';
grant select on cinshopdb.product_image to 'cinshop_user'@'%';

grant insert on cinshopdb.contact to 'cinshop_user'@'%';

grant select,insert,update,delete on cinshopdb.cart_item to 'cinshop_user'@'%';
grant select,insert,update,delete on cinshopdb.favorite_product to 'cinshop_user'@'%';
grant select,insert,update,delete on cinshopdb.credit to 'cinshop_user'@'%';
grant select,insert,update,delete on cinshopdb.review to 'cinshop_user'@'%';

grant select,insert on cinshopdb.order_detail to 'cinshop_user'@'%';
grant select,insert on cinshopdb.orders to 'cinshop_user'@'%';

grant select,insert,update on cinshopdb.customer to 'cinshop_user'@'%';
grant select,insert,update on cinshopdb.address to 'cinshop_user'@'%';


