from datetime import timezone, timedelta, datetime
offset = timedelta(hours=9)
c = datetime.now(tz=timezone(offset))
print(c.strftime("%Y-%m-%d"))