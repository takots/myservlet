reop
```sql
--reop4 db2 p080 a18760310101 请协助查询该账号是否有还未操作的出款订单 请协助调整成目前客户能操作状态

-- sql1 id
select r.draw_record_id as id, r.create_time, r.order_no, r.draw_status, r.remark
from mny_draw_record r
join ge_station g on g.account1 = r.stockholder_id
where draw_status = 0
and g.durl = 'p080'
and r.member_account = 'a18760310101'
order by create_time desc;

update mny_draw_record
set create_time = current_timestamp
where draw_record_id = (sql1的id);
``