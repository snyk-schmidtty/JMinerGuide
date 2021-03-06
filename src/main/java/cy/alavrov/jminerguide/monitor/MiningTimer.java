/*
 * Copyright (c) 2015, Andrey Lavrov <lavroff@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package cy.alavrov.jminerguide.monitor;

/**
 * Countdown timer.
 * @author Andrey Lavrov <lavroff@gmail.com>
 */
public class MiningTimer {
    private final long endAt;
    private final long clearAt;
    private volatile boolean wasAlarm;
    private final int seconds;
    
    public MiningTimer(int seconds, int secondsToClear) {
        long now = System.currentTimeMillis();
        long duration = seconds * 1000; // seconds to millis
        long clearDuration = secondsToClear * 1000; // same
        endAt = now + duration;
        clearAt = now + duration + clearDuration;
        wasAlarm = false;
        this.seconds = seconds;
    }
        
    public boolean isFinished() {
        return System.currentTimeMillis() >= endAt;
    }
    
    public boolean isOkToClear() {
        return System.currentTimeMillis() >= clearAt;
    }
    
    public int getRemainingSeconds() {        
        long remainingMillis = endAt - System.currentTimeMillis();
        if (remainingMillis < 1) return 0;
        
        int secs = (int) (remainingMillis / 1000);
        return secs;
    }
    
    public boolean wasAlarm() {
        return wasAlarm;
    }
    
    public void markAlarm() {
        wasAlarm = true;
    }

    public int getSeconds() {
        return seconds;
    }        
}
