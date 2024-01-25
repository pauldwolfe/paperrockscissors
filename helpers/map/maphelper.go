package maphelper


func Mapkey(m map[string]int, value int) (key string, ok bool) {
	for k, v := range m {
	  if v == value { 
		key = k
		ok = true
		return
	  }
	}
	return
  }